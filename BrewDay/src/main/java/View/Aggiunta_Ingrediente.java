package View;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import controller.ControllerIngredienti;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.fieldassist.AutoCompleteField;
import org.eclipse.jface.fieldassist.ComboContentAdapter;

public class Aggiunta_Ingrediente {

	protected Shell shell;
	private Text text;
	private ControllerIngredienti controller;
	private Shell modalWindow;
	public GenericObserver observer;
	
	public Aggiunta_Ingrediente(ControllerIngredienti c,GenericObserver observer) {
		controller = c;
		modalWindow = null;
		this.observer = observer;
	}
	
	public Aggiunta_Ingrediente(ControllerIngredienti c,GenericObserver observer,Shell modalWindow) {
		controller = c;
		this.modalWindow = modalWindow;
		this.observer = observer;
	}
	
	public Aggiunta_Ingrediente() {
		controller = null;
	}
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			
			Aggiunta_Ingrediente window = new Aggiunta_Ingrediente();
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
		
		Label lblAggiungiIngrediente = new Label(shell, SWT.NONE);
		lblAggiungiIngrediente.setBounds(160, 10, 118, 15);
		lblAggiungiIngrediente.setText("Aggiungi ingrediente");
		
		Label lblInserireNomeIngrediente = new Label(shell, SWT.NONE);
		lblInserireNomeIngrediente.setBounds(10, 47, 141, 15);
		lblInserireNomeIngrediente.setText("Inserire nome ingrediente");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(10, 66, 76, 21);
		
		Label lblInserireUnitDi = new Label(shell, SWT.NONE);
		lblInserireUnitDi.setBounds(10, 106, 130, 15);
		lblInserireUnitDi.setText("Inserire unit\u00E0 di misura");
		
		
		
		
		String [] items = {"litri", "grammi"};
		Combo combo = new Combo(shell, SWT.NONE);
		combo.setBounds(10, 127, 91, 23);
		combo.setItems(items);
		/*new AutoCompleteField(combo, new ComboContentAdapter(), new String[] {"litri", "grammi"});*/
		
		
		
		
		
		Label lblInserireQuantit = new Label(shell, SWT.NONE);
		lblInserireQuantit.setBounds(10, 179, 130, 15);
		lblInserireQuantit.setText("Inserire quantit\u00E0");
		
		Spinner spinner = new Spinner(shell, SWT.BORDER);
		spinner.setBounds(10, 201, 91, 22);
		spinner.setMaximum(1000000000);
		
		Button btnAggiungi = new Button(shell, SWT.NONE);
		btnAggiungi.addSelectionListener(new SelectionAdapter() {
		    @Override
		    public void widgetSelected(SelectionEvent e) {
		    	String response = controller.aggiungiIngrediente(text.getText(), Integer.parseInt(spinner.getText()),combo.getText());
				if(response.equals("Ok")) {
			
					observer.update();
				}
				else {
					MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Errore", response);
					
				}
				shell.close();
				
		    }
		});

		btnAggiungi.setBounds(295, 198, 75, 25);
		btnAggiungi.setText("Aggiungi");

	}
}
