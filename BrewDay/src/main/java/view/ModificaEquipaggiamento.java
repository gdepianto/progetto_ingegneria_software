package view;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import controller.ControllerEquipaggiamento;
import model.Equipaggiamento;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ModificaEquipaggiamento extends Window{

	private Text text;
	private ControllerEquipaggiamento controller;
	private Equipaggiamento eq;
	private GenericObserver observer;
	

	//private Shell shell;
	public ModificaEquipaggiamento(ControllerEquipaggiamento c, Equipaggiamento equip) {
		super();
		controller = c;
		eq = equip;
		//shell = new Shell();
		//createContents(shell);
	}
	

	
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
		shell.setText("Modifica equipaggiamento");
		
		Label lblNomeEquipaggiamento = new Label(shell, SWT.NONE);
		lblNomeEquipaggiamento.setBounds(10, 58, 169, 30);
		lblNomeEquipaggiamento.setText("Nome Equipaggiamento :");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(221, 55, 127, 21);
		text.setText(eq.getNome());
		
		Label lblCapacitEquipaggiamento = new Label(shell, SWT.NONE);
		lblCapacitEquipaggiamento.setText("Capacit\u00E0 Equipaggiamento :");
		lblCapacitEquipaggiamento.setBounds(10, 118, 205, 30);
		
		Text textCapacita = new Text(shell, SWT.BORDER);
		textCapacita.setBounds(221, 111, 127, 22);
		textCapacita.setText(""+eq.getCapacita());
		textCapacita.addVerifyListener(floatVerifyListener);
		
		
		
		Button btnAggiorna = new Button(shell, SWT.NONE);
		
		btnAggiorna.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if(text.getText().isEmpty() || textCapacita.getText().isEmpty()) {
						MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Errore", "Compilare tutti i campi");
			        	  
					}
					else {
						controller.aggiornaEquipaggiamento(text.getText(), Float.parseFloat(textCapacita.getText()));
						observer.update();
						shell.close();
					}
				}catch (NumberFormatException ex) {
		    		MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Errore", "La capacit\u00E0 deve essere in formato valido");
		    	}
				
			}
		});
		btnAggiorna.setBounds(172, 192, 92, 39);
		btnAggiorna.setText("Aggiorna");
		
		Label lblModificaEquipaggiamento = new Label(shell, SWT.NONE);
		lblModificaEquipaggiamento.setBounds(108, 10, 186, 25);
		lblModificaEquipaggiamento.setText("Modifica Equipaggiamento");
		
		

	}
}
