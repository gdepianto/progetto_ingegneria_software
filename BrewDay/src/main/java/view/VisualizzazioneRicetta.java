package view;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import controller.ControllerEquipaggiamento;
import controller.ControllerIngredienti;
import controller.ControllerRicetta;
import model.Equipaggiamento;
import model.Ingrediente;
import model.Ricetta;

import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.widgets.Label;

public class VisualizzazioneRicetta implements GenericObserver {

	protected Shell shell;
	private Table table;
	private ControllerRicetta controller;
	private TableViewer viewer;
	private VisualizzazioneRicetta instance;
	private Button button;
	private Equipaggiamento equip;
	
	

	public VisualizzazioneRicetta(ControllerRicetta c) {
		controller = c;
		instance = this;
	}
	
	public VisualizzazioneRicetta() {
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
			VisualizzazioneRicetta window = new VisualizzazioneRicetta();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
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
	 * Launch the application.
	 * @param args
	 */
	protected void createContents(Shell s) {
		
		equip=controller.getControllerEquipaggiamento().getEquipaggiamento();
		
		shell = s;
		
		shell.setSize(838, 300);
		shell.setText("SWT Application");
		ArrayList <Ricetta> listaRicette = controller.getRicette();
		
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
		table.setBounds(0, 0, 538, 261);
		
		Label lblNomeEquipaggiamento = new Label(shell, SWT.NONE);
		lblNomeEquipaggiamento.setBounds(583, 139, 136, 15);
		lblNomeEquipaggiamento.setText("Nome equipaggiamento:");
		
		Label lblCapacit = new Label(shell, SWT.NONE);
		lblCapacit.setBounds(583, 196, 55, 15);
		lblCapacit.setText("Capacit\u00E0:");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(583, 161, 136, 15);
		lblNewLabel.setText(equip.getNome());
		
		
		Label label = new Label(shell, SWT.NONE);
		label.setBounds(583, 217, 136, 15);
		label.setText(""+equip.getCapacita());
		
		TableColumn column = new TableColumn(tableViewer.getTable(), SWT.NONE);
        column.setText("Nome");
        column.setWidth(200);
       
        TableViewerColumn nameCol = new TableViewerColumn(tableViewer, column);
        
        nameCol.setLabelProvider(new ColumnLabelProvider(){

            @Override
            public String getText(Object element) {
                Ricetta p = (Ricetta)element;

                return p.getNome();
            }

        });
		column = new TableColumn(tableViewer.getTable(), SWT.NONE);
        column.setText("Actions");
        column.setWidth(200);
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
		    		    	tableViewer.setInput(listaRicette);
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
	
	        		    	
	        		    	ModificaRicetta finestraModifica = new ModificaRicetta(controller, p);
	        		    	finestraModifica.setObserver(instance);
	        		    	finestraModifica.open();
	
	        		    }
	        		});
		            
		            
		            Button buttonVisualize = new Button(composite,SWT.NONE);
		            buttonVisualize.setText("Visualizza");
		            buttonVisualize.addSelectionListener(new SelectionAdapter() {
	        		    @Override
	        		    public void widgetSelected(SelectionEvent e) {
	        		    	MostraRicetta FinestraRicetta = new MostraRicetta(controller, p);
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

		
		Button btnAggiungiRicetta = new Button(shell, SWT.NONE);
		btnAggiungiRicetta.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AggiuntaRicetta finestraAggiunta = new AggiuntaRicetta(controller);
				finestraAggiunta.setObserver(instance);
				finestraAggiunta.open();
			}
		});
		btnAggiungiRicetta.setBounds(583, 11, 193, 25);
		btnAggiungiRicetta.setText("Aggiungi Ricetta");
		
		Button btnVisualizzaIngrediente = new Button(shell, SWT.NONE);
		btnVisualizzaIngrediente.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Visualizzazione_Ingrediente finestraIngredienti = new Visualizzazione_Ingrediente(controller.getControllerIngredienti());
				finestraIngredienti.open();
			}
		});
		btnVisualizzaIngrediente.setBounds(583, 42, 193, 25);
		btnVisualizzaIngrediente.setText("Visualizza ingrediente");
		Button btnModificaEquip = new Button(shell, SWT.NONE);
		btnModificaEquip.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ModificaEquipaggiamento modEquip = new ModificaEquipaggiamento(controller.getControllerEquipaggiamento(),controller.getControllerEquipaggiamento().getEquipaggiamento());
				modEquip.addObserver(instance);
				modEquip.open();
						
			}
		});
		btnModificaEquip.setBounds(583, 73, 193, 25);
		btnModificaEquip.setText("Modifica equipaggiamento");
		Button btnBirraGiorno = new Button(shell, SWT.NONE);
		btnBirraGiorno.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Ricetta ric = controller.getBirraDelGiorno();
				MostraRicetta FinestraRicetta = new MostraRicetta(controller, ric);
		    	FinestraRicetta.open();	
			}
		});
		btnBirraGiorno.setBounds(583, 104, 193, 25);
		btnBirraGiorno.setText("Birra del Giorno");
		
		
		tableViewer.setInput(listaRicette);
  }

	@Override
	public void update() {
		// TODO Auto-generated method stub
		for(Control c : shell.getChildren())
			c.dispose();
		createContents(shell);
		shell.redraw(0, 0, shell.getBounds().width,
				shell.getBounds().height, true);
		
		shell.update();
		shell.layout();
	}
}

