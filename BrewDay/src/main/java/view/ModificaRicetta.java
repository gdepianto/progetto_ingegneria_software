package view;

import java.util.ArrayList;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import controller.ControllerRicetta;
import model.Ingrediente;
import model.Quantita;
import model.Ricetta;




public class ModificaRicetta extends ManipolaRicetta{

	private Text text;
	private Text text_1;
	private GenericObserver observer;
	private Ricetta ricetta;
	
	
	
	public ModificaRicetta(ControllerRicetta controller, Ricetta ricetta) {
		super(controller);
		this.ricetta = ricetta;
	}
	

	public void setObserver(GenericObserver observer) {
		this.observer = observer;
	}


	/**
	 * Create contents of the window.
	 */
	@Override
	protected void createContents(Shell s) {
		super.createContents(s);
		shell.setSize(957, 357);
		shell.setText("Modifica ricetta");
		
		
		
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(10, 10, 70, 17);
		lblNewLabel.setText("Nome");
		
		text = new Text(shell, SWT.BORDER);
		text.setToolTipText("Inserisci nome");
		text.setBounds(10, 33, 117, 25);
		text.setText(ricetta.getNome());
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(173, 10, 219, 17);
		lblNewLabel_1.setText("Tempo preparazione (giorni)");
		
		Spinner spinner = new Spinner(shell, SWT.BORDER);
		spinner.setBounds(164, 35, 117, 35);
		spinner.setSelection(ricetta.getTempoPreparazione());
		
		text_1 = new Text(shell, SWT.BORDER | SWT.MULTI);
		text_1.setBounds(10, 119, 468, 135);
		text_1.setText(ricetta.getDescrizione());
		
		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setBounds(10, 95, 184, 17);
		lblNewLabel_2.setText("Descrizione ricetta");
		
		
		Label lblNewLabel_3 = new Label(shell, SWT.NONE);
		lblNewLabel_3.setBounds(512, 10, 184, 17);
		lblNewLabel_3.setText("Seleziona ingredienti");
		
		
		
		
		tableViewer.setInput(listaIngredienti);
		for(Ingrediente ing : listaIngredienti) {
			for(Quantita q : ricetta.getIngredienti()) {
				if(q.getIngrediente().getNome().equals(ing.getNome())) {
					listaTextBox.get(listaIngredienti.indexOf(ing)).setText(""+q.getQuantitaNecessaria());
					tableViewer.getTable().getItems()[listaIngredienti.indexOf(ing)].setChecked(true);
				}
					
			}
		}
		

		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setBounds(634, 226, 147, 28);
		btnNewButton.setText("Modifica ricetta");
		btnNewButton.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) {
        		try {
        			boolean contr = true;
	        		ArrayList<Quantita> listaQuantita = new ArrayList<Quantita>();
	        		
	        		TableItem [] items = tableViewer.getTable().getItems();
	        	    for (int i = 0; i < items.length; ++i) {
	        	      if (items[i].getChecked()) {
	        	    	  if(listaTextBox.get(i).getText().isEmpty() ) {
	        	    		  MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Errore", "Non lasciare ingrediente selezionato con quantita vuota");
	        	    		  contr = false;
	        	    	  }
	        	    	  else {
		        	    	  Quantita q = new Quantita();
		        	    	  q.setIngrediente(listaIngredienti.get(i));
		        	    	  q.setQuantitaNecessaria(Float.parseFloat(listaTextBox.get(i).getText()));
		        	    	  listaQuantita.add(q);
	        	    	  }
	        	    	  
	        	      }
	        	    }
	        	    if(listaQuantita.size() == 0) {
	        	    	contr = false;
	        	    	MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Errore", "Selezionare almeno un ingrediente");
	        	    }
	        	    if(text.getText().isEmpty() || text_1.getText().isEmpty()) {
	        	    	contr = false;
	        	    	MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Errore", "Compilare tutti i campi");
	        	    }
	        	    if(contr) {
	        	    	controller.aggiornaRicetta(ricetta.getIdRicetta(),text.getText(), text_1.getText(), Integer.parseInt(spinner.getText()), listaQuantita);
	        	    	observer.update();
	        	    	shell.close();
	        	    }
        		}catch(NumberFormatException ex) {
        	 		MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Errore", "La qantit\u00E0 deve essere un numero valido");
	        	    
        		}
        	    
        	   
        	}
        		
        });

	}

}
