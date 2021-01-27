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

public class CreaPassword extends Window{

	private Text password1Text;
	private Text password2Text;
	private SecurityController controller;
	
	
	
	public CreaPassword(SecurityController controller) {
		super();
		this.controller = controller;
	}



	/**
	 * Create contents of the window.
	 */
	@Override
	protected void createContents(Shell s) {
		super.createContents(s);
		shell.setSize(450, 300);
		shell.setText("Creazione password");
		shell.setLayout(null);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(32, 29, 189, 30);
		lblNewLabel.setText("Inserisci Password");
		
		password1Text = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		password1Text.setToolTipText("Inserisci password");
		password1Text.setBounds(32, 65, 73, 25);
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(32, 119, 212, 30);
		lblNewLabel_1.setText("Conferma Password");
		
		password2Text = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		password2Text.setToolTipText("Conferma password");
		password2Text.setBounds(32, 173, 73, 25);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(!password1Text.getText().equals(password2Text.getText())) {
					MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Errore", "Password diverse!");
				}
				else {
					if(password1Text.getText().length() < 5){
						MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Errore", "Password deve essere di almeno 5 caratteri!");
					}
					else {
						controller.savePassword(password1Text.getText());
						Display.getDefault().asyncExec(new Runnable() {
					     	
	                        @Override
	                        public void run() {
	                        	
	                        	
	                        	shell.close();
	                        	
	                        }
	                    });
						BrewDayApplication.initialize(password1Text.getText());
						
					}
				}
					
				
			}
		});
		btnNewButton.setBounds(247, 213, 147, 28);
		btnNewButton.setText("Aggiungi password");

	}
}
