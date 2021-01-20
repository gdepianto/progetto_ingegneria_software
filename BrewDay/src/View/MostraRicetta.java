package View;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import controller.ControllerRicetta;
import model.Equipaggiamento;
import model.Ingrediente;
import model.Quantita;
import model.Ricetta;

import java.util.ArrayList;

import org.eclipse.jface.viewers.ArrayContentProvider;
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
		shell.setSize(453, 551);
		shell.setText("SWT Application");
		
		Label lblNome = new Label(shell, SWT.NONE);
		lblNome.setBounds(10, 10, 48, 15);
		lblNome.setText("Nome :");
		
		
		
		Label lblTempoPreparazione = new Label(shell, SWT.NONE);
		lblTempoPreparazione.setBounds(10, 42, 162, 25);
		lblTempoPreparazione.setText("Tempo Preparazione :");
		
		Label label = new Label(shell, SWT.NONE);
		label.setBounds(70, 10, 299, 25);
		label.setText(ricetta.getNome());
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setBounds(269, 42, 117, 25);
		label_1.setText(""+ricetta.getTempoPreparazione());
		
		Label lblIngredienti = new Label(shell, SWT.NONE);
		lblIngredienti.setBounds(10, 78, 162, 24);
		lblIngredienti.setText("Ingredienti :");
		
		TableViewer tableViewer = new TableViewer(shell, SWT.BORDER | SWT.FULL_SELECTION);
		tableViewer.setContentProvider(new ArrayContentProvider());
		table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(10, 108, 414, 159);
		
		TableColumn column = new TableColumn(tableViewer.getTable(), SWT.NONE);
        column.setText("Nome");
        column.setWidth(100);
       
        TableViewerColumn nameCol = new TableViewerColumn(tableViewer, column);
        
        nameCol.setLabelProvider(new ColumnLabelProvider(){

            @Override
            public String getText(Object element) {
                Quantita w = (Quantita)element;
                Ingrediente p = w.getIngrediente();
                return p.getNome();
            }

        });
        
        column = new TableColumn(tableViewer.getTable(), SWT.NONE);
        column.setText("Quantita");
        column.setWidth(100);
        TableViewerColumn quantitaCol = new TableViewerColumn(tableViewer, column);
        quantitaCol.setLabelProvider(new ColumnLabelProvider(){

            @Override
            public String getText(Object element) {
               
            	Quantita w = (Quantita)element;
            	return ""+w.getQuantitaNecessaria();
            	
            	
            }

        });
        
        column = new TableColumn(tableViewer.getTable(), SWT.NONE);
        column.setText("Unita di misura");
        column.setWidth(100);
        TableViewerColumn unitaMisuraCol = new TableViewerColumn(tableViewer, column);
        unitaMisuraCol.setLabelProvider(new ColumnLabelProvider(){

            @Override
            public String getText(Object element) {
               
            	Quantita w = (Quantita)element;
            	return w.getIngrediente().getUnitaMisura();
            	
            	
            }

        });
        
        Label lblProcedimento = new Label(shell, SWT.NONE);
        lblProcedimento.setBounds(10, 279, 152, 15);
        lblProcedimento.setText("Procedimento:");
        
        Label label_2 = new Label(shell, SWT.NONE);
        label_2.setBounds(10, 305, 414, 204);
        label_2.setText(ricetta.getDescrizione());
        
        tableViewer.setInput(ricetta.getIngredienti());
		
		

	}
}
