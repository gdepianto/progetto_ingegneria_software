package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;


import controller.ControllerRicetta;
import model.Quantita;
import model.Ricetta;

public class VisualizzaListaSpesa {

	protected Shell shell;
	private Table table;
	private ControllerRicetta controller;
	

	public VisualizzaListaSpesa(ControllerRicetta controller) {
		this.controller = controller;
	}
	
	public VisualizzaListaSpesa() {
		this.controller = null;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			VisualizzaListaSpesa window = new VisualizzaListaSpesa();
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
		shell.setSize(1116, 523);
		shell.setText("SWT Application");
		ArrayList<Ricetta> listaRicetta = controller.getRicetteScarsaDisponibilita();
		ArrayList<Entry<Ricetta,Quantita>> listaRicettaQuantita = new ArrayList<Entry<Ricetta,Quantita>>();
		
		for(Ricetta r : listaRicetta) {
			for(Quantita q : r.getIngredienti()) {
				listaRicettaQuantita.add(Map.entry(r, q));
			}
		}
	
		TableViewer tableViewer = new TableViewer(shell);
		tableViewer.setContentProvider(new ArrayContentProvider());
		table = tableViewer.getTable();
		table.addListener(SWT.MeasureItem, new Listener() {
            public void handleEvent(Event event) {
                 
                event.height = 50;
            }
        });
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(0, 0, 1079, 481);
		
		
		TableColumn column = new TableColumn(tableViewer.getTable(), SWT.NONE);
        column.setText("Ingrediente");
        column.setWidth(200);
       
        TableViewerColumn ingCol = new TableViewerColumn(tableViewer, column);
        
        ingCol.setLabelProvider(new ColumnLabelProvider(){

            @Override
            public String getText(Object element) {
                Entry<Ricetta,Quantita> p = (Entry<Ricetta,Quantita> )element;

                return p.getValue().getIngrediente().getNome();
            }

        });
        
        column = new TableColumn(tableViewer.getTable(), SWT.NONE);
        column.setText("Disponibilita");
        column.setWidth(200);
       
        TableViewerColumn dispCol = new TableViewerColumn(tableViewer, column);
        
        dispCol.setLabelProvider(new ColumnLabelProvider(){

            @Override
            public String getText(Object element) {
                Entry<Ricetta,Quantita> p = (Entry<Ricetta,Quantita> )element;

                return ""+p.getValue().getIngrediente().getDisponibilita();
            }

        });
        
        column = new TableColumn(tableViewer.getTable(), SWT.NONE);
        column.setText("Ricetta");
        column.setWidth(200);
       
        TableViewerColumn ricCol = new TableViewerColumn(tableViewer, column);
        
        ricCol.setLabelProvider(new ColumnLabelProvider(){

            @Override
            public String getText(Object element) {
                Entry<Ricetta,Quantita> p = (Entry<Ricetta,Quantita> )element;

                return p.getKey().getNome();
            }

        });
        
        column = new TableColumn(tableViewer.getTable(), SWT.NONE);
        column.setText("Quantita necessaria");
        column.setWidth(200);
       
        TableViewerColumn quantNecCol = new TableViewerColumn(tableViewer, column);
        
        quantNecCol.setLabelProvider(new ColumnLabelProvider(){

            @Override
            public String getText(Object element) {
                Entry<Ricetta,Quantita> p = (Entry<Ricetta,Quantita> )element;

                return "" + p.getValue().getQuantitaNecessaria();
            }

        });
        
        column = new TableColumn(tableViewer.getTable(), SWT.NONE);
        column.setText("Unita di Misura");
        column.setWidth(200);
       
        TableViewerColumn unitaMisCol = new TableViewerColumn(tableViewer, column);
        
        unitaMisCol.setLabelProvider(new ColumnLabelProvider(){

            @Override
            public String getText(Object element) {
                Entry<Ricetta,Quantita> p = (Entry<Ricetta,Quantita> )element;

                return "" + p.getValue().getIngrediente().getUnitaMisura();
            }

        });
        
        tableViewer.setInput(listaRicettaQuantita);
	}

}
