package View;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import controller.ControllerRicetta;
import model.Ingrediente;
import model.Quantita;
import model.Ricetta;

import java.util.ArrayList;

import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;

public class MostraRicetta {

	protected Shell shell;
	private Table table;
	private ControllerRicetta controller;
	private Ricetta ricetta;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MostraRicetta window = new MostraRicetta();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public MostraRicetta(ControllerRicetta c, Ricetta r) {
		controller = c;
		ricetta=r;
	}
	
	public MostraRicetta() {
		controller = null;
		ricetta = null;
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
		shell.setSize(450, 468);
		shell.setText("SWT Application");
		
		Label lblNome = new Label(shell, SWT.NONE);
		lblNome.setBounds(10, 10, 55, 15);
		lblNome.setText("Nome :");
		
		
		
		Label lblTempoPreparazione = new Label(shell, SWT.NONE);
		lblTempoPreparazione.setBounds(10, 42, 115, 15);
		lblTempoPreparazione.setText("Tempo Preparazione :");
		
		Label label = new Label(shell, SWT.NONE);
		label.setBounds(70, 10, 73, 15);
		label.setText(ricetta.getNome());
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setBounds(131, 42, 55, 15);
		label_1.setText(""+ricetta.getTempoPreparazione());
		
		Label lblIngredienti = new Label(shell, SWT.NONE);
		lblIngredienti.setBounds(10, 63, 63, 15);
		lblIngredienti.setText("Ingredienti :");
		
		TableViewer tableViewer = new TableViewer(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(10, 84, 414, 85);
		
		TableColumn column = new TableColumn(tableViewer.getTable(), SWT.NONE);
        column.setText("Nome");
        column.setWidth(100);
       
        TableViewerColumn firstNameCol = new TableViewerColumn(tableViewer, column);
        
        firstNameCol.setLabelProvider(new ColumnLabelProvider(){

            @Override
            public String getText(Object element) {
                Quantita w = (Quantita)element;
                Ingrediente p = w.getIngrediente();
                return p.getNome();
            }

        });
        
        column = new TableColumn(tableViewer.getTable(), SWT.NONE);
        column.setText("Quantità");
        column.setWidth(100);
        TableViewerColumn lastNameCol = new TableViewerColumn(tableViewer, column);
        lastNameCol.setLabelProvider(new ColumnLabelProvider(){

            @Override
            public String getText(Object element) {
               
            	Quantita w = (Quantita)element;
            	return ""+w.getQuantitaNecessaria();
            	
            	
            }

        });
        
        Label lblProcedimento = new Label(shell, SWT.NONE);
        lblProcedimento.setBounds(10, 194, 88, 15);
        lblProcedimento.setText("Procedimento:");
        
        Label label_2 = new Label(shell, SWT.NONE);
        label_2.setBounds(10, 215, 414, 204);
        label_2.setText(ricetta.getDescrizione());
        
        
		
		

	}
}
