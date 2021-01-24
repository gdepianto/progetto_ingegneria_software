package view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import model.Lotto;
import model.NotaGusto;

import org.eclipse.swt.widgets.Label;

import java.text.SimpleDateFormat;

import org.eclipse.swt.SWT;

public class MostraLotto {

	protected Shell shell;
	private Lotto lotto;
	
	public MostraLotto(Lotto lotto) {
		this.lotto = lotto;
	}
	
	public MostraLotto() {
		this.lotto = null;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MostraLotto window = new MostraLotto();
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
		shell.setSize(592, 340);
		shell.setText("SWT Application");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(341, 175, 147, 17);
		lblNewLabel.setText("Equipaggiamento:");
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(10, 36, 518, 112);
		lblNewLabel_1.setText(lotto.getCommento());
		
		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setBounds(316, 223, 70, 17);
		lblNewLabel_2.setText("Nome");
		
		Label lblNewLabel_3 = new Label(shell, SWT.NONE);
		lblNewLabel_3.setBounds(435, 223, 114, 17);
		lblNewLabel_3.setText(lotto.getEquipaggiamento().getNome());
		
		Label lblNewLabel_4 = new Label(shell, SWT.NONE);
		lblNewLabel_4.setBounds(316, 264, 70, 17);
		lblNewLabel_4.setText("Capacita");
		
		Label lblNewLabel_5 = new Label(shell, SWT.NONE);
		lblNewLabel_5.setBounds(435, 264, 93, 17);
		lblNewLabel_5.setText(""+lotto.getEquipaggiamento().getCapacita());
		
		Label lblNewLabel_6 = new Label(shell, SWT.NONE);
		lblNewLabel_6.setBounds(21, 175, 147, 17);
		lblNewLabel_6.setText("Quantita prodotta: ");
		
		Label lblNewLabel_7 = new Label(shell, SWT.NONE);
		lblNewLabel_7.setBounds(200, 175, 70, 17);
		lblNewLabel_7.setText(""+lotto.getQuantitaProdotta());
		
		Label lblNewLabel_8 = new Label(shell, SWT.NONE);
		lblNewLabel_8.setBounds(21, 223, 79, 17);
		lblNewLabel_8.setText("Valutazione");
		
		Label lblNewLabel_9 = new Label(shell, SWT.NONE);
		lblNewLabel_9.setBounds(200, 223, 70, 17);
		
		Label lblNewLabel_10 = new Label(shell, SWT.NONE);
		lblNewLabel_10.setBounds(10, 10, 114, 17);
		lblNewLabel_10.setText(sdf.format(lotto.getData()));
		if(lotto instanceof NotaGusto)
			lblNewLabel_9.setText(""+((NotaGusto)lotto).getValutazione());
		else
			lblNewLabel_9.setText("-");

	}
}
