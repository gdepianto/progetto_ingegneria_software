package view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import controller.SecurityController;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Accedi {

	protected Shell shell;
	private Text text;
	private SecurityController controller;
	
	
	
	public Accedi(SecurityController controller) {
		super();
		this.controller = controller;
	}
	
	public Accedi() {
	
	}
	/**
	 * Launch the application.
	 * @param args
	 */
	
	
	public static void main(String[] args) {
		try {
			Accedi window = new Accedi();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
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
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		shell.setLayout(null);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(176, 34, 70, 17);
		lblNewLabel.setText("Accedi");
		
		text = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		text.setToolTipText("Inserisci password");
		text.setBounds(176, 82, 73, 25);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if(controller.verifyPassword(text.getText())) {
					MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Password Corretta", "Verrà avviata l'applicazione");
					
					Display.getDefault().asyncExec(new Runnable() {
				     	
	                        @Override
	                        public void run() {
	                        	
	                        	
	                        	shell.close();
	                        	
	                        }
	                    });
					BrewDayApplication.startApplication(text.getText());
					
					
				}
				else {
					MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Errore", "NONONONONO");
					
				}
				
			}
		});
		btnNewButton.setBounds(176, 158, 95, 28);
		btnNewButton.setText("Accedi");

	}

	
}
