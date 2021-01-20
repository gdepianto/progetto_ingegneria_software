package View;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;

import controller.ControllerEquipaggiamento;

import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Button;

public class CreaEquipaggiamento {

	protected Shell shell;
	private Text text;
	private ControllerEquipaggiamento controller;
	

	public CreaEquipaggiamento(ControllerEquipaggiamento controller) {
		this.controller = controller;
	}
	
	public CreaEquipaggiamento() {
		this.controller = null;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			CreaEquipaggiamento window = new CreaEquipaggiamento();
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
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(53, 27, 115, 17);
		lblNewLabel.setText("Nome equipaggiamento");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(53, 61, 73, 25);
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(53, 112, 170, 17);
		lblNewLabel_1.setText("Capacita equipaggiamento");
		
		Spinner spinner = new Spinner(shell, SWT.BORDER);
		spinner.setBounds(53, 164, 130, 37);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setBounds(218, 217, 202, 28);
		btnNewButton.setText("Inserisci Equipaggiamento");

		btnNewButton.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) {
        		if(text.getText().isEmpty()) {
        			MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Errore", "Nome non deve essere vuoto");
        		}
        		else {
        			if(Integer.parseInt(spinner.getText()) <= 0) {
        				MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Errore", "Capacita deve essere maggiore di zero");
        			}
        			else {
        				String response = controller.aggiungiEquipaggiamento(text.getText(), Integer.parseInt(spinner.getText()));
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
        					MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Errore", "Capacita deve essere maggiore di zero");
        				}
        			}
        				
        		}
        	}
        });
		
	}
}
