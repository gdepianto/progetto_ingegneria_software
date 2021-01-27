package view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import controller.ControllerIngredienti;
import model.Ingrediente;

import org.eclipse.swt.widgets.TableColumn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;

public class VisualizzazioneIngrediente extends Window implements GenericObserver {

	private ControllerIngredienti controller;
	private TableViewer viewer;
	private VisualizzazioneIngrediente instance;
	
	
	//private Shell shell;
	public VisualizzazioneIngrediente(ControllerIngredienti c) {
		super();
		controller = c;
		instance = this;
		//shell = new Shell();
		//createContents(shell);
	}
	
	
	
	
	public void updateTableViewer()
	{
	    if(viewer != null)
	        viewer.refresh();
	}


	/**
	 * Create contents of the window.
	 */
	@Override
	protected void createContents(Shell s) {
		super.createContents(s);
	
		shell.setSize(721, 293);
		shell.setText("Visualizza ingredienti");
		ArrayList<Ingrediente> listaIngredienti = controller.getIngredienti();
		
		
		shell.setLayout(null);

        viewer = new TableViewer(shell);
        Table table_1 = viewer.getTable();
      
        table_1.setBounds(0, 0, 538, 261);
        // resize the row height using a MeasureItem listener
        table_1.addListener(SWT.MeasureItem, new Listener() {
            public void handleEvent(Event event) {
                 
                event.height = 50;
            }
        });
        viewer.getTable().setHeaderVisible(true);
        viewer.getTable().setLinesVisible(true);
        viewer.setContentProvider(new ArrayContentProvider());

        TableColumn column = new TableColumn(viewer.getTable(), SWT.NONE);
        column.setText("Ingrediente");
        column.setWidth(110);
       
        TableViewerColumn firstNameCol = new TableViewerColumn(viewer, column);
        
        firstNameCol.setLabelProvider(new ColumnLabelProvider(){

            @Override
            public String getText(Object element) {
                Ingrediente p = (Ingrediente)element;

                return p.getNome();
            }

        });

        column = new TableColumn(viewer.getTable(), SWT.NONE);
        column.setText("Disponibilit\u00E0");
        column.setWidth(110);
        TableViewerColumn lastNameCol = new TableViewerColumn(viewer, column);
        lastNameCol.setLabelProvider(new ColumnLabelProvider(){

            @Override
            public String getText(Object element) {
               Ingrediente p = (Ingrediente)element;

                return ""+ p.getDisponibilita();
            }

        });

        column = new TableColumn(viewer.getTable(), SWT.NONE);
        column.setText("Unit\u00E0 Misura");
        column.setWidth(110);
        TableViewerColumn unitNameCol = new TableViewerColumn(viewer, column);
        unitNameCol.setLabelProvider(new ColumnLabelProvider(){

            @Override
            public String getText(Object element) {
               Ingrediente p = (Ingrediente)element;

                return ""+ p.getUnitaMisura();
            }

        });



        column = new TableColumn(viewer.getTable(), SWT.NONE);
        column.setText("");
        column.setWidth(150);
        TableViewerColumn actionsNameCol = new TableViewerColumn(viewer, column);
        
        actionsNameCol.setLabelProvider(new ColumnLabelProvider(){
            //make sure you dispose these buttons when viewer input changes
            Map<Object, Composite> compositesAction = new HashMap<Object, Composite>();


            @Override
            public void update(ViewerCell cell) {

            	 TableItem item = (TableItem) cell.getItem();
     	        Composite composite;
     	        if(compositesAction.containsKey(cell.getElement()))
     	        {
     	            composite = compositesAction.get(cell.getElement());
     	        }
     	        else
     	        {
     	           Ingrediente p = (Ingrediente)cell.getElement();
     	        	composite = new Composite((Composite) cell.getViewerRow().getControl(),SWT.NONE);
     	        	composite.setLayout(new RowLayout());
     	        	
     	            Button buttonRemove = new Button(composite,SWT.NONE);
     	            
     	        
     	            buttonRemove.addSelectionListener(new SelectionAdapter() {
     	    		    @Override
     	    		    public void widgetSelected(SelectionEvent e) {
     	    		    	
     	    		    	
     	    		    	/**/
     	    		    	
     	    		    	
     	    		    	controller.rimuoviIngrediente(p.getIdIngrediente() );
     	    		    	listaIngredienti.remove(p);
     	    		    	
     	    		    	viewer.setInput(listaIngredienti);
     	    		    	composite.dispose();
     	    		    	Display.getDefault().asyncExec(new Runnable() {
     	
     	                        @Override
     	                        public void run() {
     	                        	for(Ingrediente i : listaIngredienti)
     	                        		System.out.println(i);
     	                        	
     	                        	
     	                        	updateTableViewer();
     	                        	
     	                        }
     	                    });
     	    		    }
     	    		});
     	            buttonRemove.setText("Rimuovi");
     	            Button buttonUpdate = new Button(composite,SWT.NONE);
     	            buttonUpdate.setText("Aggiorna");
     	            buttonUpdate.addSelectionListener(new SelectionAdapter() {
             		    @Override
             		    public void widgetSelected(SelectionEvent e) {

             		    	
             		    	ModificaIngrediente FinestraModifica = new ModificaIngrediente(controller, p);
             		    	FinestraModifica.addObserver(instance);
             		    	FinestraModifica.open();

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
        
        Button btnNewButton = new Button(shell, SWT.NONE);
        btnNewButton.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) {
        		AggiuntaIngrediente finestraAggiungiIngrediente = new AggiuntaIngrediente(controller,instance);
        		finestraAggiungiIngrediente.open();
        	}
        });
        btnNewButton.setBounds(558, 34, 153, 28);
        btnNewButton.setText("Aggiungi Ingrediente");

        
        

        viewer.setInput(listaIngredienti);
       
	
		
		
		
		

	}
	
	

	@Override
	public void update() {
		for(Control c : shell.getChildren())
			c.dispose();
		createContents(shell);
		shell.redraw(0, 0, shell.getBounds().width,
				shell.getBounds().height, true);
		
		shell.update();
		shell.layout();
		
		
	}
}
