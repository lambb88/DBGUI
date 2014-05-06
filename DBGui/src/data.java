import java.awt.Color;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;

import swing2swt.layout.BorderLayout;

import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Table;


public class data extends FormPage {
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Label lblAssessmentItem;
	private Label lblSemester;
	private Label lblCriterion;
	private Label lblStudents;
	private FormText formText;
	private Image image;
	private Button btnSubmit;
	private Table table;

	/**
	 * Create the form page.
	 * @param id
	 * @param title
	 */
	public data(String id, String title) {
		super(id, title);
	}

	/**
	 * Create the form page.
	 * @param editor
	 * @param id
	 * @param title
	 * @wbp.parser.constructor
	 * @wbp.eval.method.parameter id "Some id"
	 * @wbp.eval.method.parameter title "Some title"
	 */
	public data(FormEditor editor, String id, String title) {
		super(editor, id, title);
	}

	/**
	 * Create contents of the form.
	 * @param managedForm
	 */
	@Override
	protected void createFormContent(IManagedForm managedForm) {
		FormToolkit toolkit = managedForm.getToolkit();
		ScrolledForm form = managedForm.getForm();
		form.setText("AI Query Builder");
		Composite body = form.getBody();
		toolkit.decorateFormHeading(form.getForm());
		toolkit.paintBordersFor(body);
		managedForm.getForm().getBody().setLayout(new GridLayout(2, false));
		
		formText = managedForm.getToolkit().createFormText(managedForm.getForm().getBody(), false);
		managedForm.getToolkit().paintBordersFor(formText);
		formText.setText("", false, false);
		new Label(managedForm.getForm().getBody(), SWT.NONE);
		
		lblAssessmentItem = managedForm.getToolkit().createLabel(managedForm.getForm().getBody(), "Assessment Item", SWT.NONE);
		lblAssessmentItem.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		
		text = new Text(managedForm.getForm().getBody(), SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		managedForm.getToolkit().adapt(text, true, true);
		
		lblSemester = managedForm.getToolkit().createLabel(managedForm.getForm().getBody(), "Semester", SWT.NONE);
		lblSemester.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		
		text_1 = new Text(managedForm.getForm().getBody(), SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		text_1.setSize(15, 15);
		managedForm.getToolkit().adapt(text_1, true, true);
		
		lblCriterion = managedForm.getToolkit().createLabel(managedForm.getForm().getBody(), "Criterion", SWT.NONE);
		lblCriterion.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		
		text_2 = new Text(managedForm.getForm().getBody(), SWT.BORDER);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		managedForm.getToolkit().adapt(text_2, true, true);
		
		lblStudents = managedForm.getToolkit().createLabel(managedForm.getForm().getBody(), "Student(s)", SWT.NONE);
		lblStudents.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		
		text_3 = new Text(managedForm.getForm().getBody(), SWT.BORDER);
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		managedForm.getToolkit().adapt(text_3, true, true);
		
		btnSubmit = managedForm.getToolkit().createButton(managedForm.getForm().getBody(), "Submit", SWT.NONE);
		GridData gd_btnSubmit = new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1);
		gd_btnSubmit.widthHint = 585;
		btnSubmit.setLayoutData(gd_btnSubmit);
		
		table = managedForm.getToolkit().createTable(managedForm.getForm().getBody(), SWT.NONE);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		managedForm.getToolkit().paintBordersFor(table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
	}

}
