package view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;

import controller.ControllerEquipaggiamento;

import org.eclipse.swt.widgets.Button;

public class CreaEquipaggiamento extends Window{

	private Text nomeText;
	private ControllerEquipaggiamento controller;
	
	//private Shell shell;
	public CreaEquipaggiamento(ControllerEquipaggiamento controller) {
		super();
		this.controller = controller;
		//shell = new Shell();
		//createContents(shell);
	}
	



	/**
	 * Create contents of the window.
	 */
	@Override
	protected void createContents(Shell s) {
		super.createContents(s);
		shell.setSize(450, 300);
		shell.setText("Creazione equipaggiamento");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(53, 27, 202, 17);
		lblNewLabel.setText("Nome equipaggiamento:");
		
		nomeText = new Text(shell, SWT.BORDER);
		nomeText.setBounds(53, 61, 73, 25);
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(53, 112, 202, 17);
		lblNewLabel_1.setText("Capacit\u00E0 equipaggiamento:");
		
		Text capacitaText = new Text(shell, SWT.BORDER);
		capacitaText.setBounds(53, 164, 130, 37);
		capacitaText.addVerifyListener(floatVerifyListener);;
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setBounds(218, 217, 202, 28);
		btnNewButton.setText("Inserisci Equipaggiamento");

		btnNewButton.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) {
        		try {
	        		if(nomeText.getText().isEmpty()) {
	        			MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Attenzione", "Nome non deve essere vuoto");
	        		}
	        		else {
	        			if(Float.parseFloat(capacitaText.getText()) <= 0) {
	        				MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Errore", "Capacit\u00E0 deve essere maggiore di zero");
	        			}
	        			else {
	        				String response = controller.aggiungiEquipaggiamento(nomeText.getText(), Float.parseFloat(capacitaText.getText()));
	        				if(response.equals("Ok")){
		        				Display.getDefault().asyncExec(new Runnable() {
							     	
			                        @Override
			                        public void run() {
			                        	
			                        	
			                        	shell.close();
			                        	
			                        }
			                    });
		        				BrewDayApplication.startApplication(BrewDayApplication.password);
	        				}
	        				else {
	        					MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Errore", response);
	        				}
	        			}
	        				
	        		}
        		}catch(NumberFormatException ex) {
        			MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Errore", "La capacit\u00E0 deve essere un numero valido");
        		}
        	}
        });
		
	}
}
