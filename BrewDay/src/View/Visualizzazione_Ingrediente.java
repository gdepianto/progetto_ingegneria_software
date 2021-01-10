package View;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
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

public class Visualizzazione_Ingrediente implements GenericObserver {

	protected Shell shell;
	private Table table;
	private ControllerIngredienti controller;
	private TableViewer viewer;
	private Visualizzazione_Ingrediente instance;
	
	public Visualizzazione_Ingrediente(ControllerIngredienti c) {
		controller = c;
		instance = this;
	}
	
	public Visualizzazione_Ingrediente() {
		controller = null;
		instance = this;
	}
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Visualizzazione_Ingrediente window = new Visualizzazione_Ingrediente();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void updateTableViewer()
	{
	    if(viewer != null)
	        viewer.refresh();
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents(new Shell());
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
	protected void createContents(Shell s) {
		shell = s;
	
		shell.setSize(721, 293);
		shell.setText("SWT Application");
		ArrayList<Ingrediente> listaIngredienti = controller.getIngredienti();
		
		
		shell.setLayout(null);

        viewer = new TableViewer(shell);
        Table table_1 = viewer.getTable();
      
        table_1.setBounds(0, 0, 503, 261);
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
        column.setWidth(100);
       
        TableViewerColumn firstNameCol = new TableViewerColumn(viewer, column);
        
        firstNameCol.setLabelProvider(new ColumnLabelProvider(){

            @Override
            public String getText(Object element) {
                Ingrediente p = (Ingrediente)element;

                return p.getNome();
            }

        });

        column = new TableColumn(viewer.getTable(), SWT.NONE);
        column.setText("Disponibilit�");
        column.setWidth(100);
        TableViewerColumn lastNameCol = new TableViewerColumn(viewer, column);
        lastNameCol.setLabelProvider(new ColumnLabelProvider(){

            @Override
            public String getText(Object element) {
               Ingrediente p = (Ingrediente)element;

                return ""+ p.getDisponibilita();
            }

        });

        column = new TableColumn(viewer.getTable(), SWT.NONE);
        column.setText("Unit� Misura");
        column.setWidth(100);
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
        column.setWidth(100);
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
     	        	composite = new Composite((Composite) cell.getViewerRow().getControl(),SWT.NONE);
     	        	composite.setLayout(new RowLayout());
     	        	
     	            Button buttonRemove = new Button(composite,SWT.NONE);
     	            buttonRemove.setText("Remove");
     	            Ingrediente p = (Ingrediente)cell.getElement();
     	            buttonRemove.addSelectionListener(new SelectionAdapter() {
     	    		    @Override
     	    		    public void widgetSelected(SelectionEvent e) {
     	    		    	
     	    		    	
     	    		    	/**/
     	    		    	
     	    		    	
     	    		    	controller.rimuoviIngrediente(p.getIdIngrediente() );
     	    		    	listaIngredienti.remove(p);
     	    		    	
     	    		    	//buttons.remove(p);
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
     	            Button buttonUpdate = new Button(composite,SWT.NONE);
     	            buttonUpdate.setText("Aggiorna");
     	            buttonUpdate.addSelectionListener(new SelectionAdapter() {
             		    @Override
             		    public void widgetSelected(SelectionEvent e) {

             		    	
             		    	Modifica_Ingrediente FinestraModifica = new Modifica_Ingrediente(controller, p);
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
        		Aggiunta_Ingrediente finestraAggiungiIngrediente = new Aggiunta_Ingrediente(controller,instance,shell);
        		finestraAggiungiIngrediente.open();
        	}
        });
        btnNewButton.setBounds(520, 34, 153, 28);
        btnNewButton.setText("Aggiungi Ingrediente");

        
        

        viewer.setInput(listaIngredienti);
       
	
		
		
		
		

	}
	
	

	@Override
	public void update() {
		System.out.println("sfsdf");
		createContents(shell);
		shell.redraw();
		shell.pack();
		
	}
}
