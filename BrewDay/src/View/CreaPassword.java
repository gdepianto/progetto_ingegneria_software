package View;

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

public class CreaPassword {

	protected Shell shell;
	private Text text;
	private Text text_1;
	private SecurityController controller;
	
	
	
	public CreaPassword(SecurityController controller) {

		this.controller = controller;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			CreaPassword window = new CreaPassword();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public CreaPassword() {
		
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
		lblNewLabel.setBounds(32, 29, 189, 30);
		lblNewLabel.setText("Inserisci Password");
		
		text = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		text.setToolTipText("Inserisci password");
		text.setBounds(32, 65, 73, 25);
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(32, 119, 212, 30);
		lblNewLabel_1.setText("Conferma Password");
		
		text_1 = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		text_1.setToolTipText("Conferma password");
		text_1.setBounds(32, 173, 73, 25);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(!text.getText().equals(text_1.getText())) {
					MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Errore", "Password diverse!");
				}
				else {
					if(text.getText().length() < 5){
						MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Errore", "Password deve essere di almeno 5 caratteri!");
					}
					else {
						controller.savePassword(text.getText());
						BrewDayApplication.initialize(text.getText());
						shell.close();
					}
				}
					
				
			}
		});
		btnNewButton.setBounds(247, 213, 147, 28);
		btnNewButton.setText("Aggiungi password");

	}
}
