package View;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import controller.ControllerIngredienti;
import controller.ControllerRicetta;
import model.Ingrediente;
import model.Ricetta;

import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;

public class visualizzazioneRicetta {

	protected Shell shell;
	private Table table;
	private ControllerRicetta controller;
	private TableViewer viewer;
	private visualizzazioneRicetta instance;
	private Button btnAggiungiRicetta;
	
	
	
	public visualizzazioneRicetta(ControllerRicetta c) {
		controller = c;
		instance = this;
	}
	
	public visualizzazioneRicetta() {
		controller = null;
		instance = this;
	}
	
	public void updateTableViewer()
	{
	    if(viewer != null)
	        viewer.refresh();
	}

	public static void main(String[] args) {
		try {
			visualizzazioneRicetta window = new visualizzazioneRicetta();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
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
	 * Launch the application.
	 * @param args
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		ArrayList <Ricetta> listaRicette = controller.getRicette();
		
		TableViewer tableViewer = new TableViewer(shell);
		table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(0, 0, 295, 261);
		
		
		TableColumn column = new TableColumn(tableViewer.getTable(), SWT.NONE);
        column.setText("Nome");
        column.setWidth(100);
		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewer, column);
		
		
		tableViewerColumn_1.setLabelProvider(new ColumnLabelProvider(){

			Map<Object, Composite> compositesAction = new HashMap<Object, Composite>();

		public void update(ViewerCell cell) {

       	 TableItem item = (TableItem) cell.getItem();
	        Composite composite;
	        if(compositesAction.containsKey(cell.getElement()))
	        {
	            composite = compositesAction.get(cell.getElement());
	        }
	        else
	        {
	        	composite = new Composite((Composite) cell.getViewerRow().getControl(),SWT.NONE);
	        	composite.setLayout(new RowLayout());
	        	
	            Button buttonRemove = new Button(composite,SWT.NONE);
	            buttonRemove.setText("Remove");
	            Ricetta p = (Ricetta)cell.getElement();
	            buttonRemove.addSelectionListener(new SelectionAdapter() {
	    		    @Override
	    		    public void widgetSelected(SelectionEvent e) {
	    		    	
	    		    	
	    		    	/**/
	    		    	
	    		    	
	    		    	controller.rimuoviRicetta(p.getIdRicetta() );/**/
	    		    	listaRicette.remove(p);
	    		    	
	    		    	//buttons.remove(p);
	    		    	viewer.setInput(listaRicette);
	    		    	composite.dispose();
	    		    	Display.getDefault().asyncExec(new Runnable() {
	
	                        @Override
	                        public void run() {
	                        	for(Ricetta r : listaRicette)
	                        		System.out.println(r);
	                        	
	                        	
	                        	updateTableViewer();
	                        	
	                        }
	                    });
	    		    }
	    		});
	            
	            Button buttonUpdate = new Button(composite,SWT.NONE);
	            buttonUpdate.setText("Modifica");
	            buttonUpdate.addSelectionListener(new SelectionAdapter() {
        		    @Override
        		    public void widgetSelected(SelectionEvent e) {

        		    	
        		    	/*Modifica_Ingrediente FinestraModifica = new Modifica_Ingrediente(controller, p);
        		    	FinestraModifica.addObserver(instance);
        		    	FinestraModifica.open();*/

        		    }
        		});
	            
	            
	            Button buttonVisualize = new Button(composite,SWT.NONE);
	            buttonVisualize.setText("Visualizza");
	            buttonVisualize.addSelectionListener(new SelectionAdapter() {
        		    @Override
        		    public void widgetSelected(SelectionEvent e) {
        		    	mostraRicetta FinestraRicetta = new mostraRicetta(controller, p);
        		    	FinestraRicetta.open();
        		    }
        		});
	            
	            
	            
	            compositesAction.put(cell.getElement(), composite);
	        }
	        TableEditor editor = new TableEditor(item.getParent());
	        editor.grabHorizontal  = true;
	        editor.grabVertical = true;
	        editor.setEditor(composite , item, cell.getColumnIndex());
	        editor.layout();
       }

	});
		
		btnAggiungiRicetta = new Button(shell, SWT.NONE);
		btnAggiungiRicetta.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
			}
		});
		btnAggiungiRicetta.setBounds(327, 115, 97, 25);
		btnAggiungiRicetta.setText("Aggiungi Ricetta");
  }

	/**
	 * Open the window.
	 */
	
	
	
	
	

	/**
	 * Create contents of the window.
	 */
	
   
   
   
   


		
		
		
	
		
		
   


		
		
		
		

	}

