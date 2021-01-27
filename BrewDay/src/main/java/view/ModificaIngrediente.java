package view;

import org.eclipse.swt.widgets.Combo;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import controller.ControllerIngredienti;
import model.Ingrediente;

import org.eclipse.swt.widgets.Label;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;



public class ModificaIngrediente extends Window{

	private ControllerIngredienti controller;
	private Text text;
	private Ingrediente i;
	private GenericObserver observer;
	
	
	//private Shell shell;
	public ModificaIngrediente(ControllerIngredienti c, Ingrediente ing) {
		super();
		controller = c;
		i = ing;
		//shell = new Shell();
		//createContents(shell);
	}
	
	

	/**
	 * Launch the application.
	 * @param args
	 */
	
	public void addObserver(GenericObserver obs) {
		observer = obs;
	}
	
	
	/**
	 * Create contents of the window.
	 */
	@Override
	protected void createContents(Shell s) {
		super.createContents(s);
		shell.setSize(450, 300);
		shell.setText("Modifica ingrediente");
		
		Label lblModificaIngrediente = new Label(shell, SWT.NONE);
		lblModificaIngrediente.setBounds(158, 10, 168, 20);
		lblModificaIngrediente.setText("Modifica Ingrediente");
		
		Label lblModificaNome = new Label(shell, SWT.NONE);
		lblModificaNome.setBounds(10, 39, 197, 20);
		lblModificaNome.setText("Modifica nome ingrediente");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(10, 65, 91, 21);
		text.setText(i.getNome());
		
		Label lblModificaUnitDi = new Label(shell, SWT.NONE);
		lblModificaUnitDi.setBounds(10, 100, 241, 20);
		lblModificaUnitDi.setText("Modifica unit\u00E0 di misura ingrediente");
		
		String [] items = {"litri", "grammi"};
		Combo combo = new Combo(shell, SWT.NONE);
		combo.setBounds(10, 127, 91, 23);
		combo.setItems(items);
		combo.setText(i.getUnitaMisura());
		
		Label lblModificaQuantit = new Label(shell, SWT.NONE);
		lblModificaQuantit.setBounds(10, 170, 175, 20);
		lblModificaQuantit.setText("Modifica quantit\u00E0");
		
		Text disponibilitaText = new Text(shell, SWT.BORDER);
		disponibilitaText.setBounds(10, 201, 91, 22);
		disponibilitaText.setText(""+i.getDisponibilita());
		disponibilitaText.addVerifyListener(floatVerifyListener);
		
		Button btnModifica = new Button(shell, SWT.NONE);
		btnModifica.addSelectionListener(new SelectionAdapter() {
		    @Override
		    public void widgetSelected(SelectionEvent e) {
		    	try {
			    	if(combo.getText().equals(""))
							MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Errore", "Selezionare obbligatoriamente un'unit\u00E0 di misura");
			    	else if (disponibilitaText.getText().equals(""))
							MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Errore", "Selezionare obbligatoriamente una qantit\u00E0");
			    	else if (text.getText().equals(""))
			    			MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Errore", "Selezionare obbligatoriamente un nome");
			    	else {
				    	controller.aggiornaIngrediente(i.getIdIngrediente(), text.getText(), Float.parseFloat(disponibilitaText.getText()),combo.getText());
						observer.update();
						shell.close();
			    	}
		    	}catch(NumberFormatException ex) {
		    		MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Errore", "La qantit\u00E0 deve essere un numero valido");
				    
		    	}
		    }
		});
		
		btnModifica.setBounds(318, 198, 75, 25);
		btnModifica.setText("Modifica");
		
		

	}
}
