package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Window {

	protected Shell shell;
	protected VerifyListener floatVerifyListener;
	protected Display display;
	
	public Window() {
		floatVerifyListener = new VerifyListener() {

            @Override
            public void verifyText(VerifyEvent e) {
                // allows cut (CTRL + x)
                if (e.text.isEmpty()) {
                    e.doit = true;
                } else if (e.keyCode == SWT.ARROW_LEFT ||
                            e.keyCode == SWT.ARROW_RIGHT ||
                            e.keyCode == SWT.BS ||
                            e.keyCode == SWT.DEL ||
                            e.keyCode == SWT.CTRL ||
                            e.keyCode == SWT.SHIFT) {
                    e.doit = true;
                } else {
                    boolean allow = false;
                    for (int i = 0; i < e.text.length(); i++) {
                        char c = e.text.charAt(i);
                        allow = Character.isDigit(c) || c=='.';
                        if ( ! allow ) {
                            break;
                        }
                    }
                    e.doit = allow;
                }

            }
        };
	}
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Window window = new Window();
			window.open();
		} catch (Exception e) {
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		display = Display.getDefault();
		createContents(new Shell());
		shell.setImage(new Image(display, "resources/icona.png"));
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents(Shell s) {
		shell = s;
	}

}
