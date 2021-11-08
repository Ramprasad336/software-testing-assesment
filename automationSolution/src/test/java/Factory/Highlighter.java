package Factory;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class Highlighter {

    private static JavascriptExecutor js;
    private static List<WebElement> lastElements = new ArrayList<>();
    private static List<String> removeHighlight = new ArrayList<>();
    private static final Logger LOG = LoggerFactory.getLogger(Highlighter.class);

    // modifying the background color instead of adding a pixel border to ensure that selenium can click the element
    // when using the border it sometimes happens that the element is moved y the top border and is not longer clickable
    private static String highlightBackground = "arguments[0].style.background='orange'";
    private static String highlightBorder = "arguments[0].style.border='2px solid red'";
    private static String unHighlight = "arguments[0].style.%s='%s'";

    public static void highlightElement(WebElement element) {
    	String highlightWith = "";
        String unhighlightWith = "";
//        String highlightColor = System.getProperty("testautomation.highlightColor");
       String highlightColor = "red";
        if (highlightColor != null) {
        	highlightBackground = "arguments[0].style.background='"+highlightColor+"'";
        	highlightBorder = "arguments[0].style.border='2px solid "+highlightColor+"'";
        }
//        boolean highlightElement = Boolean.parseBoolean(System.getProperty("testautomation.highlightElement"));
        boolean highlightElement = Boolean.parseBoolean("true");
        
        // to deactivate the highlighting of elements by property
        if (!highlightElement) return;
        
        // init the driver and the js before doing anything
        WebDriver driver = driverFactory.getDriver();
        js = (JavascriptExecutor) driver;

        // unhighlight the last element
        unhighlightLast();
        
        // get desired mode from properties
//        String mode = System.getProperty("testautomation.highlightStyle");
        String mode = "background";
        	
        if(mode.equals("border"))
            highlightWith = highlightBorder;
        else if(mode.equals("background"))
            highlightWith = highlightBackground;
        else
        	Assert.fail("No highlighting mode called: " + mode);
        
        // remember the current style
        String elementStyle = (String)js.executeScript(String.format("return arguments[0].style.%s", mode), element);
        unhighlightWith = String.format(unHighlight, mode, elementStyle);
        
        // remember the new element
        lastElements.add(element);

        // remember how to unhighlight the element
        removeHighlight.add(unhighlightWith);
   
        // try to highlight the element
        try {
            js.executeScript(highlightWith, element);
        } catch (Exception all){
            // do nothing
        }
       
    }

    public static void unhighlightLast() {
        if (!lastElements.isEmpty()) {
            try {
                // if there already is a highlighted element, unhighlight it
                for (int i = 0; i < lastElements.size(); i++) {
                    js.executeScript(removeHighlight.get(i), lastElements.get(i));
                }
            } catch (Exception ignored) {
                // the page got reloaded, the element isn't there
            } finally {
                // element either restored or wasn't valid, nullify in both cases
            	lastElements = new ArrayList<>();
                removeHighlight = new ArrayList<>();
            }
        }
    }
}
