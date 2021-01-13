package View;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Text;

import controller.ControllerRicetta;
import model.Ingrediente;
import model.Quantita;
import model.Ricetta;

import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import org.eclipse.swt.widgets.Composite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.widgets.Button;

public class AggiuntaRicetta {

	protected Shell shell;
	private Text text;
	private Text text_1;
	private Table table;
	private ControllerRicetta controller;
	private GenericObserver observer;

	public AggiuntaRicetta(ControllerRicetta controller) {
		this.controller = controller;
	}


	public void setObserver(GenericObserver observer) {
		this.observer = observer;
	}

	public AggiuntaRicetta() {
		this.controller = null;
	}
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AggiuntaRicetta window = new AggiuntaRicetta();
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
		shell.setSize(921, 309);
		shell.setText("SWT Application");
		ArrayList<Ingrediente> listaIngredienti = controller.getControllerIngredienti().getIngredienti();
		ArrayList<Text> listaTextBox = new ArrayList<Text>();
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(10, 10, 70, 17);
		lblNewLabel.setText("Nome");
		
		text = new Text(shell, SWT.BORDER);
		text.setToolTipText("inserisci nome");
		text.setBounds(10, 33, 117, 25);
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(173, 10, 219, 17);
		lblNewLabel_1.setText("Tempo preparazione (giorni)");
		
		Spinner spinner = new Spinner(shell, SWT.BORDER);
		spinner.setBounds(164, 35, 117, 35);
		
		text_1 = new Text(shell, SWT.BORDER | SWT.MULTI);
		text_1.setBounds(10, 119, 468, 135);
		
		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setBounds(10, 95, 184, 17);
		lblNewLabel_2.setText("Descrizione ricetta");
		
		Label lblNewLabel_3 = new Label(shell, SWT.NONE);
		lblNewLabel_3.setBounds(512, 10, 184, 17);
		lblNewLabel_3.setText("Seleziona ingredienti");
		
		
		
		TableViewer tableViewer = new TableViewer(shell,SWT.CHECK | SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL);
		tableViewer.getTable().setHeaderVisible(true);
        tableViewer.getTable().setLinesVisible(true);
		tableViewer.setContentProvider(new ArrayContentProvider());
		table = tableViewer.getTable();
		table.setBounds(512, 47, 399, 144);
		
		
		TableColumn column = new TableColumn(tableViewer.getTable(), SWT.NONE);
        column.setText("Ingrediente");
        column.setWidth(100);
       
        TableViewerColumn nameCol = new TableViewerColumn(tableViewer, column);
        
        nameCol.setLabelProvider(new ColumnLabelProvider(){

            @Override
            public String getText(Object element) {
                Ingrediente p = (Ingrediente)element;

                return p.getNome();
            }

        });
        
        column = new TableColumn(tableViewer.getTable(), SWT.NONE);
        column.setText("Quantità necessaria");
        column.setWidth(100);
       
        TableViewerColumn quantitaCol = new TableViewerColumn(tableViewer, column);
       
        quantitaCol.setLabelProvider(new ColumnLabelProvider(){
            //make sure you dispose these buttons when viewer input changes
            Map<Object, Text> textBoxes = new HashMap<Object, Text>();


            @Override
            public void update(ViewerCell cell) {

                TableItem item = (TableItem) cell.getItem();
                Text textBox;
                if(textBoxes.containsKey(cell.getElement()))
                {
                	textBox = textBoxes.get(cell.getElement());
                }
                else
                {
                	textBox = new Text((Composite) cell.getViewerRow().getControl(),SWT.BORDER);
                	textBox.addVerifyListener(new VerifyListener() {

                        @Override
                        public void verifyText(VerifyEvent e) {
                            // allows cut (CTRL + x)
                            if (e.text.isEmpty()) {
                                e.doit = true;
                            } else if (e.keyCode == SWT.ARROW_LEFT ||
                                        e.keyCode == SWT.ARROW_RIGHT ||
                                        e.keyCode == SWT.BS ||
                                        e.keyCode == SWT.DEL ||
                                        e.keyCode == SWT.CTRL ||
                                        e.keyCode == SWT.SHIFT) {
                                e.doit = true;
                            } else {
                                boolean allow = false;
                                for (int i = 0; i < e.text.length(); i++) {
                                    char c = e.text.charAt(i);
                                    allow = Character.isDigit(c) || c=='.';
                                    if ( ! allow ) {
                                        break;
                                    }
                                }
                                e.doit = allow;
                            }

                        }
                    });
                	textBoxes.put(cell.getElement(), textBox);
                	listaTextBox.add(textBox);
                }
                TableEditor editor = new TableEditor(item.getParent());
                editor.grabHorizontal  = true;
                editor.grabVertical = true;
                editor.setEditor(textBox , item, cell.getColumnIndex());
                editor.layout();
            }

        });
        column = new TableColumn(tableViewer.getTable(), SWT.NONE);
        column.setText("Unita di misura");
        column.setWidth(300);
       
        TableViewerColumn unitaMisuraCol = new TableViewerColumn(tableViewer, column);
        
        unitaMisuraCol.setLabelProvider(new ColumnLabelProvider(){

            @Override
            public String getText(Object element) {
                Ingrediente p = (Ingrediente)element;

                return p.getUnitaMisura();
            }

        });
		
		
		tableViewer.setInput(listaIngredienti);
		

		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setBounds(634, 226, 147, 28);
		btnNewButton.setText("Aggiungi ricetta");
		btnNewButton.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) {
        		ArrayList<Quantita> listaQuantita = new ArrayList<Quantita>();
        		
        		TableItem [] items = tableViewer.getTable().getItems();
        	    for (int i = 0; i < items.length; ++i) {
        	      if (items[i].getChecked()) {
        	    	  Quantita q = new Quantita();
        	    	  q.setIngrediente(listaIngredienti.get(i));
        	    	  q.setQuantitaNecessaria(Float.parseFloat(listaTextBox.get(i).getText()));
        	    	  listaQuantita.add(q);
        	    	  
        	      }
        	    }
        	    
        	    String response = controller.aggiungiRicetta(text.getText(), text_1.getText(), Integer.parseInt(spinner.getText()), listaQuantita);
        	    if(response.equals("Ok")) {
        	    	observer.update();
        	    }
        	    else {
        	    	MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Errore", response);
        	    }
        	    
        	}
        });

	}
}
