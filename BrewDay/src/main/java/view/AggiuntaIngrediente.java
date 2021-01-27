package view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import controller.ControllerIngredienti;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.jface.dialogs.MessageDialog;

public class AggiuntaIngrediente extends Window{


	private Text nomeIngredienteText;
	private ControllerIngredienti controller;
	public GenericObserver observer;
	
	
	
	//per fare andare WIndowBuilder scommentare
	//private Shell shell;
	public AggiuntaIngrediente(ControllerIngredienti c,GenericObserver observer) {
		super();
		controller = c;
		this.observer = observer;
		//shell = new Shell();
		//createContents(new Shell());
	}
	
	

	
	
	
	/**
	 * Create contents of the window.
	 */
	@Override
	protected void createContents(Shell s) {
		super.createContents(s);
		shell.setSize(450, 300);
		shell.setText("Aggiungi ingrediente");
		
		Label lblAggiungiIngrediente = new Label(shell, SWT.NONE);
		lblAggiungiIngrediente.setBounds(160, 10, 142, 20);
		lblAggiungiIngrediente.setText("Aggiungi ingrediente");
		
		Label lblInserireNomeIngrediente = new Label(shell, SWT.NONE);
		lblInserireNomeIngrediente.setBounds(10, 40, 186, 20);
		lblInserireNomeIngrediente.setText("Inserire nome ingrediente");
		
		nomeIngredienteText = new Text(shell, SWT.BORDER);
		nomeIngredienteText.setBounds(10, 66, 76, 21);
		
		Label lblInserireUnitDi = new Label(shell, SWT.NONE);
		lblInserireUnitDi.setBounds(10, 96, 186, 20);
		lblInserireUnitDi.setText("Inserire unit\u00E0 di misura");
		
		
		
		
		String [] items = {"litri", "grammi"};
		Combo combo = new Combo(shell, SWT.NONE);
		combo.setBounds(10, 127, 91, 23);
		combo.setItems(items);
		
		
		
		
		
		Label lblInserireQuantit = new Label(shell, SWT.NONE);
		lblInserireQuantit.setBounds(10, 169, 171, 20);
		lblInserireQuantit.setText("Inserire quantit\u00E0");
		
		Text quantitaText = new Text(shell, SWT.BORDER);
		quantitaText.setBounds(10, 201, 91, 22);
		quantitaText.addVerifyListener(floatVerifyListener);
		
		Button btnAggiungi = new Button(shell, SWT.NONE);
		btnAggiungi.addSelectionListener(new SelectionAdapter() {
		    @Override
		    public void widgetSelected(SelectionEvent e) {
		    	try {
					if(combo.getText().equals("") || nomeIngredienteText.getText().isEmpty() || quantitaText.getText().isEmpty())
						MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Errore", "Compilare tutti i campi");
		        	  
					else {
						String response = controller.aggiungiIngrediente(nomeIngredienteText.getText(), Float.parseFloat(quantitaText.getText()),combo.getText());	
				    	if(response.equals("Ok")) {
					
							observer.update();
							shell.close();
						}
						else {
							MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Errore", response);
							shell.close();
						}
					}
		    	}catch (NumberFormatException ex) {
		    		MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Errore", "La quantit\u00E0 deve essere in formato valido");
		    	}
				
				
				
				
				
		    }
		});

		btnAggiungi.setBounds(295, 198, 75, 30);
		btnAggiungi.setText("Aggiungi");

	}
}
