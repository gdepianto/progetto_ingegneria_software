package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import controller.ControllerRicetta;
import model.Ingrediente;

public class ManipolaRicetta extends Window{
	protected ControllerRicetta controller;
	protected ArrayList<Ingrediente> listaIngredienti;
	protected ArrayList<Text> listaTextBox;
	private Table table;
	protected TableViewer tableViewer;
	
	public ManipolaRicetta(ControllerRicetta controller) {
		super();

		this.controller = controller;
	}
	
	@Override
	protected void createContents(Shell s) {
		super.createContents(s);
		listaIngredienti = controller.getControllerIngredienti().getIngredienti();
		listaTextBox = new ArrayList<>();
		
		tableViewer = new TableViewer(shell,SWT.CHECK | SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL);
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
        column.setText("Quantit\u00E0 necessaria");
        column.setWidth(160);
       
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
                	textBox.addVerifyListener(floatVerifyListener);
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
        column.setText("Unit\u00E0 di misura");
        column.setWidth(140);
       
        TableViewerColumn unitaMisuraCol = new TableViewerColumn(tableViewer, column);
        
        unitaMisuraCol.setLabelProvider(new ColumnLabelProvider(){

            @Override
            public String getText(Object element) {
                Ingrediente p = (Ingrediente)element;

                return p.getUnitaMisura();
            }

        });
		
	}

}
