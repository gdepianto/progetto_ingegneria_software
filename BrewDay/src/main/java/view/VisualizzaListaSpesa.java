package view;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;


import controller.ControllerRicetta;
import model.Quantita;
import model.Ricetta;

public class VisualizzaListaSpesa extends Window{

	private Table table;
	private ControllerRicetta controller;
	

	public VisualizzaListaSpesa(ControllerRicetta controller) {
		super();
		this.controller = controller;
	}
	

	/**
	 * Create contents of the window.
	 */
	@Override
	protected void createContents(Shell s) {
		super.createContents(s);
		shell.setSize(1116, 523);
		shell.setText("Lista della spesa");
		ArrayList<Ricetta> listaRicetta = controller.getRicetteScarsaDisponibilita();
		ArrayList<Entry<Ricetta,Quantita>> listaRicettaQuantita = new ArrayList<Entry<Ricetta,Quantita>>();
		
		for(Ricetta r : listaRicetta) {
			for(Quantita q : r.getIngredienti()) {
				listaRicettaQuantita.add(Map.entry(r, q));
			}
		}
	
		TableViewer tableViewer = new TableViewer(shell);
	
		table = tableViewer.getTable();
		table.setLinesVisible(true);
		
		table.addListener(SWT.MeasureItem, new Listener() {
            public void handleEvent(Event event) {
                 
                event.height = 50;
            }
        });
		table.setHeaderVisible(true);
		table.setBounds(0, 0, 1079, 481);
		
		tableViewer.setContentProvider(new ArrayContentProvider());
		
		
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
        column.setText("Disponibilit\u00E0");
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
        column.setText("Quantit\u00E0 necessaria");
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
        column.setText("Unit\u00E0 di Misura");
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
