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
import org.eclipse.swt.graphics.Image;

public class Accedi extends Window{

	private Text passwordText;
	private SecurityController controller;
	
	
	//le righe commentate servono per fare andare il WindowBuilder
	//private Shell shell;
	public Accedi(SecurityController controller) {
		super();
		this.controller = controller;
		//shell = new Shell();
		//this.createContents(new Shell());
	}
	
	

	/**
	 * Create contents of the window.
	 */
	@Override
	protected void createContents(Shell s) {
		super.createContents(s);
		shell.setSize(450, 300);
		shell.setText("Accedi a BrewDay");
		shell.setLayout(null);
		
		passwordText = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		passwordText.setToolTipText("Inserisci password");
		passwordText.setBounds(176, 155, 95, 25);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(176, 108, 95, 17);
		lblNewLabel.setText("Accedi");
		
	
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if(controller.verifyPassword(passwordText.getText())) {
					MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Password Corretta", "Verrà avviata l'applicazione");
					
					Display.getDefault().asyncExec(new Runnable() {
				     	
	                        @Override
	                        public void run() {
	                        	
	                        	
	                        	shell.close();
	                        	
	                        }
	                    });
					BrewDayApplication.startApplication(passwordText.getText());
					
					
				}
				else {
					MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Errore", "Password errata");
					
				}
				
			}
		});
		btnNewButton.setBounds(176, 207, 95, 28);
		btnNewButton.setText("Accedi");
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(114, 0, 284, 92);
		lblNewLabel_1.setImage(new Image(display,"resources/icona.png"));

	}
}
