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

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import controller.ControllerRicetta;
import model.Equipaggiamento;
import model.Ricetta;

import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.widgets.Label;

public class VisualizzazioneRicetta extends Window implements GenericObserver {

	private Table table;
	private ControllerRicetta controller;
	private TableViewer viewer;
	private VisualizzazioneRicetta instance;
	private Equipaggiamento equip;
	
	
	//private Shell shell;
	public VisualizzazioneRicetta(ControllerRicetta c) {
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
	 * Launch the application.
	 * @param args
	 */
	@Override
	protected void createContents(Shell s) {
		super.createContents(s);
		equip=controller.getControllerEquipaggiamento().getEquipaggiamento();

		
		shell.setSize(855, 532);
		shell.setText("Visualizza ricette");
		Image image = new Image(display,"resources/icona_rettangolo.png");
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(583, 23, 236, 88);
		Image scaled = imageScale(image, 236, 88);
		lblNewLabel_1.setImage(scaled);
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
		table.setBounds(0, 0, 537, 490);
		
		Label lblNomeEquipaggiamento = new Label(shell, SWT.NONE);
		lblNomeEquipaggiamento.setBounds(583, 330, 193, 25);
		lblNomeEquipaggiamento.setText("Nome equipaggiamento:");
		
		Label lblCapacit = new Label(shell, SWT.NONE);
		lblCapacit.setBounds(583, 411, 101, 20);
		lblCapacit.setText("Capacit\u00E0:");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(583, 369, 136, 20);
		lblNewLabel.setText(equip.getNome());
		
		
		Label label = new Label(shell, SWT.NONE);
		label.setBounds(583, 457, 152, 15);
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
		            buttonRemove.setText("Rimuovi");
		            Ricetta p = (Ricetta)cell.getElement();
		            buttonRemove.addSelectionListener(new SelectionAdapter() {
		    		    @Override
		    		    public void widgetSelected(SelectionEvent e) {
		    		    	
		    		    	
		    		    	/**/
		    		    	
		    		    	
		    		    	controller.rimuoviRicetta(p.getIdRicetta() );/**/
		    		    	listaRicette.remove(p);
		    		    	
		    		    	tableViewer.setInput(listaRicette);
		    		    	composite.dispose();
		    		    	Display.getDefault().asyncExec(new Runnable() {
		
		                        @Override
		                        public void run() {
		                        	
		                        	
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
		btnAggiungiRicetta.setBounds(583, 117, 193, 30);
		btnAggiungiRicetta.setText("Aggiungi Ricetta");
		
		
		Button btnVisualizzaIngrediente = new Button(shell, SWT.NONE);
		btnVisualizzaIngrediente.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				VisualizzazioneIngrediente finestraIngredienti = new VisualizzazioneIngrediente(controller.getControllerIngredienti());
				finestraIngredienti.open();
			}
		});
		btnVisualizzaIngrediente.setBounds(583, 153, 193, 30);
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
		btnModificaEquip.setBounds(583, 189, 193, 30);
		btnModificaEquip.setText("Modifica equipaggiamento");
		Button btnBirraGiorno = new Button(shell, SWT.NONE);
		btnBirraGiorno.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Ricetta ric = controller.getBirraDelGiorno();
				if(ric.getIdRicetta() != -1) {
					MostraRicetta FinestraRicetta = new MostraRicetta(controller, ric);
			    	FinestraRicetta.open();	
				}
				else{
					 MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Errore", "Non hai disponibilit\u00E0 di ingredienti per fare nessuna ricetta!");
	        	    	
				}
			}
		});
		btnBirraGiorno.setBounds(583, 261, 193, 30);
		btnBirraGiorno.setText("Birra del Giorno");
		
		Button btnListaSpesa = new Button(shell, SWT.NONE);
		btnListaSpesa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				VisualizzaListaSpesa finestraListaSpesa = new VisualizzaListaSpesa(controller);
				finestraListaSpesa.open();
			}
		});
		btnListaSpesa.setBounds(583, 225, 193, 30);
		btnListaSpesa.setText("Lista della spesa");
		
		
		tableViewer.setInput(listaRicette);
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

