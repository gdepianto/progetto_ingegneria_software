package view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;

import java.util.Calendar;
import java.util.Date;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Text;

import controller.ControllerLotto;
import controller.ControllerRicetta;
import model.Equipaggiamento;
import model.Ricetta;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;

public class AggiuntaNota {

	protected Shell shell;
	private Text text;
	private Text text_1;
	private ControllerRicetta controller;
	private Equipaggiamento equip;
	private Ricetta ricetta;
	private GenericObserver observer;
	
	

	public AggiuntaNota(Ricetta r,ControllerRicetta controller, Equipaggiamento equip) {
		this.ricetta = r;
		this.controller = controller;
		this.equip = equip;
	}

	public AggiuntaNota() {
		this.ricetta = null;
		this.controller = null;
		this.equip = null;
	}
	
	public void setObserver(GenericObserver observer) {
		this.observer = observer;
	}
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AggiuntaNota window = new AggiuntaNota();
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
		shell.setSize(723, 348);
		shell.setText("SWT Application");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(31, 10, 175, 17);
		lblNewLabel.setText("Data produzione lotto");
		
		DateTime dateTime = new DateTime(shell, SWT.BORDER | SWT.CALENDAR);
		dateTime.setBounds(24, 53, 228, 159);
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(419, 10, 70, 17);
		lblNewLabel_1.setText("Commento");
		
		text = new Text(shell, SWT.BORDER | SWT.MULTI);
		text.setBounds(318, 53, 326, 159);
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setText(""+equip.getCapacita());
		text_1.setBounds(43, 265, 73, 25);
		text_1.addVerifyListener(new VerifyListener() {

            @Override
            public void verifyText(VerifyEvent e) {
                // allows cut (CTRL + x)
                if (e.text.isEmpty()) {
                    e.doit = true;
                } else if (e.keyCode == SWT.ARROW_LEFT ||
                            e.keyCode == SWT.ARROW_RIGHT ||
                            e.keyCode == SWT.BS ||
                            e.keyCode == SWT.DEL ||
                            e.keyCode == SWT.CTRL ||
                            e.keyCode == SWT.SHIFT) {
                    e.doit = true;
                } else {
                    boolean allow = false;
                    for (int i = 0; i < e.text.length(); i++) {
                        char c = e.text.charAt(i);
                        allow = Character.isDigit(c) || c=='.';
                        if ( ! allow ) {
                            break;
                        }
                    }
                    e.doit = allow;
                }

            }
        });
		
		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setBounds(46, 231, 124, 17);
		lblNewLabel_2.setText("Quantita prodotta");
		
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
				if(text.getText().isEmpty()) {
					MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Errore", "Non puoi lasciare il commento vuoto");
				}
				else if(text_1.getText().isEmpty()) {
					MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Errore", "Non puoi lasciare la quantita prodotta vuota");
				}
				else if(Float.parseFloat(text_1.getText()) > equip.getCapacita()) {
					MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Errore", "Non puoi produrre piu birra di quella che permette il tuo equipaggiamento!");
				}
				else {
					int val;
					boolean scalaIngredienti = false;
					MessageBox messageBox = new MessageBox(shell, SWT.ICON_QUESTION
					            | SWT.YES | SWT.NO);
					messageBox.setMessage("Vuoi aggiornare le disponibilita degli ingredienti?");
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
					controller.getControllerLotto().inserisciLotto(ricetta.getIdRicetta(),text.getText(),cal.getTime() ,Float.parseFloat( text_1.getText()), equip, val);
					if(scalaIngredienti) {
						if(controller.aggiornaDisponibilità(ricetta, Float.parseFloat( text_1.getText())/equip.getCapacita())) {
							MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Disponibilita aggiornate!", "Le disponibilita degli ingredienti sono state aggiornate!");
						}
						else {
							MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Disponibilita non aggiornate.", "Le disponibilita degli ingredienti non sono state aggiornate perchè non erano sufficienti.");
							
						}
					}
					
					observer.update();
					shell.close();
				}
			}
		});
		btnNewButton.setBounds(515, 262, 118, 28);
		btnNewButton.setText("Aggiungi Nota");
		
		

	}
}
