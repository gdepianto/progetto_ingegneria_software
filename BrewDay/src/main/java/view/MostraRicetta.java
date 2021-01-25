package view;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import controller.ControllerRicetta;
import model.Equipaggiamento;
import model.Ingrediente;
import model.Lotto;
import model.NotaGusto;
import model.Quantita;
import model.Ricetta;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;

public class MostraRicetta implements GenericObserver{

	protected Shell shell;
	private Table table;
	private ControllerRicetta controller;
	private Ricetta ricetta;
	private Table table_1;
	private TableViewer viewer;
	private MostraRicetta instance;

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
		instance = this;
	}
	
	public MostraRicetta() {
		controller = null;
		ricetta = null;
		instance = this;
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
		shell.setSize(1142, 551);
		shell.setText("SWT Application");
		
		Label lblNome = new Label(shell, SWT.NONE);
		lblNome.setBounds(10, 10, 48, 15);
		lblNome.setText("Nome :");
		
		ArrayList <Lotto> listaNote = controller.getControllerLotto().getLotti(ricetta.getIdRicetta());

		
		
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
        
        Label lblNewLabel = new Label(shell, SWT.NONE);
        lblNewLabel.setBounds(496, 10, 55, 15);
        lblNewLabel.setText("Note");
        
        TableViewer tableViewer_1 = new TableViewer(shell, SWT.BORDER | SWT.FULL_SELECTION);
        tableViewer_1.setContentProvider(new ArrayContentProvider());
        table_1 = tableViewer_1.getTable();
        table_1.addListener(SWT.MeasureItem, new Listener() {
            public void handleEvent(Event event) {
                 
                event.height = 50;
            }
        });
        table_1.setBounds(496, 42, 623, 225);
        table_1.setLinesVisible(true);
		table_1.setHeaderVisible(true);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer_1, SWT.NONE);
		TableColumn tblclmnNomeNota = tableViewerColumn.getColumn();
		tblclmnNomeNota.setWidth(100);
		tblclmnNomeNota.setText("Data");
		tableViewerColumn.setLabelProvider(new ColumnLabelProvider(){

            @Override
            public String getText(Object element) {
               
            	Lotto lo = (Lotto)element;
            	return (sdf.format(lo.getData()));
            	
            	
            }

        });
		
		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewer_1, SWT.NONE);
		TableColumn tblclmnCommento = tableViewerColumn_1.getColumn();
		tblclmnCommento.setWidth(100);
		tblclmnCommento.setText("Commento");
		tableViewerColumn_1.setLabelProvider(new ColumnLabelProvider(){

            @Override
            public String getText(Object element) {
               
            	Lotto lo = (Lotto)element;
            	String ret = (""+lo.getCommento());
            	String fin = "";
            	if(ret.length() < 12)
            		fin = ret;
            	else
            		fin = ret.substring(0, 11);
            	
            	return fin+"...";	
            	
            	
            }

        });
		
		
		
		TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(tableViewer_1, SWT.NONE);
		TableColumn tableColumn = tableViewerColumn_2.getColumn();
		tableColumn.setWidth(200);
		
		tableViewerColumn_2.setLabelProvider(new ColumnLabelProvider(){

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
			            Lotto p = (Lotto)cell.getElement();
			            buttonRemove.addSelectionListener(new SelectionAdapter() {
			    		    @Override
			    		    public void widgetSelected(SelectionEvent e) {
			    		    	
			    		    	controller.getControllerLotto().rimuoviLotto(p.getIdLotto());
			    		    	listaNote.remove(p);
			    		    	
			    		    	
			    		    	tableViewer_1.setInput(listaNote);
			    		    	composite.dispose();
			    		    	Display.getDefault().asyncExec(new Runnable() {
			
			                        @Override
			                        public void run() {
			                        	updateTableViewer();
			                        	
			                        }
			                    });
			    		    }
			    		});
			            Button buttonVisualize = new Button(composite,SWT.NONE);
				        buttonVisualize.setText("Visualizza");
				        buttonVisualize.addSelectionListener(new SelectionAdapter() {
						    @Override
						    public void widgetSelected(SelectionEvent e) {
						    	MostraLotto FinestraNota = new MostraLotto(p);
						    	FinestraNota.open();
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
		
		
		
		TableViewerColumn tableViewerColumn_3 = new TableViewerColumn(tableViewer_1, SWT.NONE);
		TableColumn tableColumn_1 = tableViewerColumn_3.getColumn();
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("Valutazione");
		tableViewerColumn_3.setLabelProvider(new ColumnLabelProvider(){

            @Override
            public String getText(Object element) {
               
            	String valutazione = "-";
            	if(element instanceof NotaGusto)
            		valutazione = (""+((NotaGusto)element).getValutazione());
            	return valutazione;
            	
            	
            }

        });
		
		Button btnAggiungiNota = new Button(shell, SWT.NONE);
		btnAggiungiNota.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AggiuntaNota finestraAggiungi = new AggiuntaNota(ricetta, controller, controller.getControllerEquipaggiamento().getEquipaggiamento());
				finestraAggiungi.setObserver(instance);
				finestraAggiungi.open();
				
			}
		});
		btnAggiungiNota.setBounds(496, 310, 117, 25);
		btnAggiungiNota.setText("Aggiungi nota");
		
		
		tableViewer.setInput(ricetta.getIngredienti());
		
		tableViewer_1.setInput(listaNote);
		
		
		
		
		
		
		
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
