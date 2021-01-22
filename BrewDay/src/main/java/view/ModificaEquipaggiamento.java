package view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import controller.ControllerEquipaggiamento;
import controller.ControllerIngredienti;
import model.Equipaggiamento;
import model.Ingrediente;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Spinner;

public class ModificaEquipaggiamento {

	protected Shell shell;
	private Text text;
	private ControllerEquipaggiamento controller;
	private Equipaggiamento eq;
	

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ModificaEquipaggiamento window = new ModificaEquipaggiamento();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ModificaEquipaggiamento(ControllerEquipaggiamento c, Equipaggiamento equip) {
		controller = c;
		eq = equip;
	}
	
	public ModificaEquipaggiamento() {
		controller = null;
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
		
		Label lblNomeEquipaggiamento = new Label(shell, SWT.NONE);
		lblNomeEquipaggiamento.setBounds(10, 58, 143, 15);
		lblNomeEquipaggiamento.setText("Nome Equipaggiamento :");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(221, 55, 127, 21);
		
		Label lblCapacitEquipaggiamento = new Label(shell, SWT.NONE);
		lblCapacitEquipaggiamento.setText("Capacit\u00E0 Equipaggiamento :");
		lblCapacitEquipaggiamento.setBounds(10, 118, 155, 15);
		
		Spinner spinner = new Spinner(shell, SWT.BORDER);
		spinner.setBounds(221, 111, 127, 22);
		
		
		Button btnAggiorna = new Button(shell, SWT.NONE);
		
		btnAggiorna.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				controller.aggiornaEquipaggiamento(text.getText(), Integer.parseInt(spinner.getText()));
				
				
			}
		});
		btnAggiorna.setBounds(172, 192, 75, 25);
		btnAggiorna.setText("Aggiorna");
		
		Label lblModificaEquipaggiamento = new Label(shell, SWT.NONE);
		lblModificaEquipaggiamento.setBounds(108, 10, 163, 15);
		lblModificaEquipaggiamento.setText("Modifica Equipaggiamento");
		
		

	}
}
