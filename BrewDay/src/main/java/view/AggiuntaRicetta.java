package view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;

import controller.ControllerRicetta;
import model.Quantita;

import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.TableItem;


import java.util.ArrayList;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Button;

public class AggiuntaRicetta extends ManipolaRicetta{

	private Text nomeText;
	private Text descrizioneText;
	
	
	private GenericObserver observer;

	//private Shell shell;
	public AggiuntaRicetta(ControllerRicetta controller) {
		super(controller);
		//shell = new Shell();
		//createContents(shell);
	}





	/**
	 * Create contents of the window.
	 */
	@Override
	protected void createContents(Shell s) {
		super.createContents(s);
		shell.setSize(921, 309);
		shell.setText("Aggiunta Ricetta");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(10, 10, 70, 17);
		lblNewLabel.setText("Nome");
		
		nomeText = new Text(shell, SWT.BORDER);
		nomeText.setToolTipText("inserisci nome");
		nomeText.setBounds(10, 33, 117, 25);
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(173, 10, 219, 17);
		lblNewLabel_1.setText("Tempo preparazione (giorni)");
		
		Spinner preparazioneSpinner = new Spinner(shell, SWT.BORDER);
		preparazioneSpinner.setBounds(164, 35, 117, 35);
		
		descrizioneText = new Text(shell, SWT.BORDER | SWT.MULTI);
		descrizioneText.setBounds(10, 119, 468, 135);
		
		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setBounds(10, 95, 184, 17);
		lblNewLabel_2.setText("Descrizione ricetta");
		
		Label lblNewLabel_3 = new Label(shell, SWT.NONE);
		lblNewLabel_3.setBounds(512, 10, 184, 17);
		lblNewLabel_3.setText("Seleziona ingredienti");
		
		
		
		
		
		tableViewer.setInput(listaIngredienti);
		

		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setBounds(634, 226, 147, 28);
		btnNewButton.setText("Aggiungi ricetta");
		btnNewButton.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) {
        		try {
	        		ArrayList<Quantita> listaQuantita = new ArrayList<Quantita>();
	        		boolean contr = true;
	        		TableItem [] items = tableViewer.getTable().getItems();
	        	    for (int i = 0; i < items.length && contr; ++i) {
	        	      if (items[i].getChecked()) {
	        	    	  if(listaTextBox.get(i).getText().isEmpty() ) {
	        	    		  MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Attenzione", "Non lasciare ingrediente selezionato con quantita vuota");
	        	    		  contr = false;
	        	    	  }
	        	    	  else {
		        	    	  Quantita q = new Quantita();

		        	    	  q.setQuantitaNecessaria(Float.parseFloat(listaTextBox.get(i).getText()));
		        	    	  q.setIngrediente(listaIngredienti.get(i));
		        	    	  listaQuantita.add(q);
	        	    	  }
	        	    	  
	        	      }
	        	    }
	        	    if(listaQuantita.size() == 0) {
	        	    	MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Errore", "Selezionare almeno un ingrediente");
	        	    	contr = false;
	        	    }
	        	    if(nomeText.getText().isEmpty() || descrizioneText.getText().isEmpty()) {
	        	    	contr = false;
	        	    	MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Errore", "Compilare tutti i campi");
	        	    }
	        	   
	        	    if(contr) {
		        	    String response = controller.aggiungiRicetta(nomeText.getText(), descrizioneText.getText(), Integer.parseInt(preparazioneSpinner.getText()), listaQuantita);
		        	    if(response.equals("Ok")) {
		        	    	observer.update();
		        	    	shell.close();
		        	    }
		        	    else {
		        	    	MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Errore", response);
		        	    }
	        	    }
	        	    
        		}catch(NumberFormatException ex) {
        	 		MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Errore", "La qantit\u00E0 deve essere un numero valido");
	        	    
        		}
        	}
        });

	}
	
	
	public void setObserver(GenericObserver observer) {
		this.observer = observer;
	}

}
