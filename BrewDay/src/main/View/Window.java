package View;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.DisposeEvent;

public class Window {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Window window = new Window();
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
		shell.setSize(561, 377);
		shell.setText("SWT Application");
		shell.setLayout(null);
		
		Label lblBrewDay = new Label(shell, SWT.NONE);
		lblBrewDay.setBounds(232, 10, 48, 15);
		lblBrewDay.setText("Brew day");
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setBounds(387, 135, 148, 45);
		btnNewButton.setText("Aggiungi Ricetta");
		
		Combo combo = new Combo(shell, SWT.NONE);
		combo.setBounds(387, 71, 148, 23);
		
		Label lblSelezionaEquipaggiamento = new Label(shell, SWT.NONE);
		lblSelezionaEquipaggiamento.setBounds(387, 45, 148, 15);
		lblSelezionaEquipaggiamento.setText("Seleziona equipaggiamento");
		
		Button btnBrewOfThe = new Button(shell, SWT.NONE);
		btnBrewOfThe.setBounds(387, 211, 148, 45);
		btnBrewOfThe.setText("Brew of the Day");
		
		ExpandBar expandBar_1 = new ExpandBar(shell, SWT.NONE);
		expandBar_1.setBounds(10, 62, 129, 32);
		
		ExpandItem xpndtmModifica = new ExpandItem(expandBar_1, SWT.NONE);
		xpndtmModifica.setExpanded(true);
		xpndtmModifica.setText("Ricetta 1");
		
		ExpandBar expandBar = new ExpandBar(shell, SWT.NONE);
		expandBar.setBounds(10, 105, 129, 32);
		
		ExpandItem xpndtmRicetta = new ExpandItem(expandBar, SWT.NONE);
		xpndtmRicetta.setText("Ricetta 2");
		
		ExpandBar expandBar_2 = new ExpandBar(shell, SWT.NONE);
		expandBar_2.setBounds(10, 161, 129, 32);
		
		ExpandItem xpndtmRicetta_1 = new ExpandItem(expandBar_2, SWT.NONE);
		xpndtmRicetta_1.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				
			}
		});
		xpndtmRicetta_1.setText("Ricetta 3");
		
		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.setBounds(387, 283, 148, 45);
		btnNewButton_1.setText("Magazzino");
		
		
		
	}
}
