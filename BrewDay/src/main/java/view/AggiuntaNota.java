package view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;

import java.util.Calendar;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Text;

import controller.ControllerRicetta;
import model.Equipaggiamento;
import model.Ricetta;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class AggiuntaNota extends Window{

	private Text commentoText;
	private Text quantitaText;
	private ControllerRicetta controller;
	private Equipaggiamento equip;
	private Ricetta ricetta;
	private GenericObserver observer;
	
	
	//private Shell shell;
	public AggiuntaNota(Ricetta r,ControllerRicetta controller, Equipaggiamento equip) {
		super();
		this.ricetta = r;
		this.controller = controller;
		this.equip = equip;
		//shell = new Shell();
		//createContents(shell);
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
		shell = new Shell();
		shell.setSize(723, 348);
		shell.setText("Aggiunta Nota");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(31, 10, 175, 17);
		lblNewLabel.setText("Data produzione lotto");
		
		DateTime dateTime = new DateTime(shell, SWT.BORDER | SWT.CALENDAR);
		dateTime.setBounds(24, 53, 228, 159);
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(419, 10, 124, 17);
		lblNewLabel_1.setText("Commento");
		
		commentoText = new Text(shell, SWT.BORDER | SWT.MULTI);
		commentoText.setBounds(318, 53, 326, 159);
		
		quantitaText = new Text(shell, SWT.BORDER);
		quantitaText.setText(""+equip.getCapacita());
		quantitaText.setBounds(43, 265, 73, 25);
		quantitaText.addVerifyListener(floatVerifyListener);
		
		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setBounds(46, 231, 124, 17);
		lblNewLabel_2.setText("Quantit\u00E0 prodotta");
		
		Spinner spinner = new Spinner(shell, SWT.BORDER);
		spinner.setEnabled(false);
		spinner.setMaximum(5);
		spinner.setBounds(318, 262, 138, 44);
		
		Button btnCheckButton = new Button(shell, SWT.CHECK);
		btnCheckButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(btnCheckButton.getSelection() == true) {
					spinner.setEnabled(true);
				}
				else {
					spinner.setEnabled(false);
				}
			}
		});
		btnCheckButton.setBounds(324, 226, 111, 22);
		btnCheckButton.setText("Voto Gusto");
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if(commentoText.getText().isEmpty()) {
						MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Errore", "Non puoi lasciare il commento vuoto");
					}
					else if(quantitaText.getText().isEmpty()) {
						MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Errore", "Non puoi lasciare la quantit\u00E0 prodotta vuota");
					}
					else if(Float.parseFloat(quantitaText.getText()) > equip.getCapacita()) {
						MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Errore", "Non puoi produrre piu birra di quella che permette il tuo equipaggiamento!");
					}
					else {
						int val;
						boolean scalaIngredienti = false;
						MessageBox messageBox = new MessageBox(shell, SWT.ICON_QUESTION
						            | SWT.YES | SWT.NO);
						messageBox.setMessage("Vuoi aggiornare le disponibilit\u00E0 degli ingredienti?");
						messageBox.setText("Aggiornamento ingredienti");
						int response = messageBox.open();
						if (response == SWT.YES)
							scalaIngredienti = true;
						if(spinner.isEnabled())
							val = Integer.parseInt(spinner.getText());
						else
							val = -1;
						Calendar cal = Calendar.getInstance();
						cal.set(Calendar.YEAR, dateTime.getYear());
						cal.set(Calendar.MONTH, dateTime.getMonth());
						cal.set(Calendar.DAY_OF_MONTH, dateTime.getDay());
						controller.getControllerLotto().inserisciLotto(ricetta.getIdRicetta(),commentoText.getText(),cal.getTime() ,Float.parseFloat( quantitaText.getText()), equip, val);
						if(scalaIngredienti) {
							if(controller.aggiornaDisponibilita(ricetta, Float.parseFloat( quantitaText.getText())/equip.getCapacita())) {
								MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Disponibilit\u00E0 aggiornate!", "Le disponibilit\u00E0 degli ingredienti sono state aggiornate!");
							}
							else {
								MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Disponibilit\u00E0 non aggiornate.", "Le disponibilit\u00E0 degli ingredienti non sono state aggiornate perch\u00E8 non erano sufficienti.");
								
							}
						}
						
						observer.update();
						shell.close();
					}
				}catch (NumberFormatException ex) {
		    		MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Errore", "La quantit\u00E0 deve essere in formato valido");
		    	}
			}
		});
		btnNewButton.setBounds(515, 262, 118, 28);
		btnNewButton.setText("Aggiungi Nota");
		
		

	}
}
