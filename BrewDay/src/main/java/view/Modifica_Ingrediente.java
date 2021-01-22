package view;

import org.eclipse.swt.widgets.Combo;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import controller.ControllerIngredienti;
import model.Ingrediente;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Button;



public class Modifica_Ingrediente {

	protected Shell shell;
	private ControllerIngredienti controller;
	private Text text;
	private Ingrediente i;
	private GenericObserver observer;
	
	public Modifica_Ingrediente(ControllerIngredienti c, Ingrediente ing) {
		controller = c;
		i = ing;
	}
	
	public Modifica_Ingrediente() {
		controller = null;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	
	public void addObserver(GenericObserver obs) {
		observer = obs;
	}
	
	public static void main(String[] args) {
		try {
			Modifica_Ingrediente window = new Modifica_Ingrediente();
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
		
		Label lblModificaIngrediente = new Label(shell, SWT.NONE);
		lblModificaIngrediente.setBounds(158, 10, 115, 15);
		lblModificaIngrediente.setText("Modifica Ingrediente");
		
		Label lblModificaNome = new Label(shell, SWT.NONE);
		lblModificaNome.setBounds(10, 44, 152, 15);
		lblModificaNome.setText("Modifica nome ingrediente");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(10, 65, 91, 21);
		text.setText(i.getNome());
		
		Label lblModificaUnitDi = new Label(shell, SWT.NONE);
		lblModificaUnitDi.setBounds(10, 111, 197, 15);
		lblModificaUnitDi.setText("Modifica unit\u00E0 di misura ingrediente");
		
		String [] items = {"litri", "grammi"};
		Combo combo = new Combo(shell, SWT.NONE);
		combo.setBounds(10, 127, 91, 23);
		combo.setItems(items);
		combo.setText(i.getUnitaMisura());
		
		Label lblModificaQuantit = new Label(shell, SWT.NONE);
		lblModificaQuantit.setBounds(10, 180, 99, 15);
		lblModificaQuantit.setText("Modifica quantit\u00E0");
		
		Spinner spinner = new Spinner(shell, SWT.BORDER);
		spinner.setBounds(10, 201, 91, 22);
		spinner.setMaximum(1000000000);
		spinner.setSelection((int)i.getDisponibilita());
		
		Button btnModifica = new Button(shell, SWT.NONE);
		btnModifica.addSelectionListener(new SelectionAdapter() {
		    @Override
		    public void widgetSelected(SelectionEvent e) {
		    	controller.aggiornaIngrediente(i.getIdIngrediente(), text.getText(), Integer.parseInt(spinner.getText()),combo.getText());
				observer.update();
		    }
		});
		
		btnModifica.setBounds(318, 198, 75, 25);
		btnModifica.setText("Modifica");
		
		

	}
}
