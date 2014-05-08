package com.example.db;
import java.awt.Color;


public class data extends FormPage {
	private Label lblAssessmentItem;
	private Label lblSemester;
	private Label lblCriterion;
	private Label lblStudents;
	private FormText formText;
	private Image image;
	private Button btnSubmit;
	private Label lblLastname;
	private Text txtWade;
	private Text txtAndrews;
	private Text txtA;
	private Text txtFa;
	private Text txtAfac;
	private Button student_btn;
	private Button ai_button;
	private Button crit_btn;
	private Table table;

	public data(String id, String title) {
		super(id, title);
	}

	public data(FormEditor editor, String id, String title) {
		super(editor, id, title);
	}

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
		lblAssessmentItem.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		txtA = managedForm.getToolkit().createText(managedForm.getForm().getBody(), "New Text", SWT.NONE);
		txtA.setText("A1");
		txtA.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblSemester = managedForm.getToolkit().createLabel(managedForm.getForm().getBody(), "Semester", SWT.NONE);
		lblSemester.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		txtFa = managedForm.getToolkit().createText(managedForm.getForm().getBody(), "New Text", SWT.NONE);
		txtFa.setText("FA12");
		txtFa.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblCriterion = managedForm.getToolkit().createLabel(managedForm.getForm().getBody(), "Criterion", SWT.NONE);
		lblCriterion.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		txtAfac = managedForm.getToolkit().createText(managedForm.getForm().getBody(), "New Text", SWT.NONE);
		txtAfac.setText("A1FA12C4");
		txtAfac.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblStudents = managedForm.getToolkit().createLabel(managedForm.getForm().getBody(), "FirstName", SWT.NONE);
		lblStudents.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		txtWade = managedForm.getToolkit().createText(managedForm.getForm().getBody(), "New Text", SWT.NONE);
		txtWade.setText("Wade");
		txtWade.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblLastname = managedForm.getToolkit().createLabel(managedForm.getForm().getBody(), "LastName", SWT.NONE);
		lblLastname.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		txtAndrews = managedForm.getToolkit().createText(managedForm.getForm().getBody(), "New Text", SWT.NONE);
		txtAndrews.setText("Andrews");
		txtAndrews.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		btnSubmit = managedForm.getToolkit().createButton(managedForm.getForm().getBody(), "Look-up by Entries", SWT.NONE);
		GridData gd_btnSubmit = new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1);
		gd_btnSubmit.widthHint = 585;
		btnSubmit.setLayoutData(gd_btnSubmit);
		
		student_btn = new Button(managedForm.getForm().getBody(), SWT.NONE);
		student_btn.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		managedForm.getToolkit().adapt(student_btn, true, true);
		student_btn.setText("All Student List");
		
		ai_button = new Button(managedForm.getForm().getBody(), SWT.NONE);
		ai_button.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		ai_button.setText("Assessment Item Scores");
		managedForm.getToolkit().adapt(ai_button, true, true);
		
		crit_btn = new Button(managedForm.getForm().getBody(), SWT.NONE);
		crit_btn.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		crit_btn.setText("Criterion Scores");
		managedForm.getToolkit().adapt(crit_btn, true, true);
		
		table = new Table(managedForm.getForm().getBody(), SWT.BORDER | SWT.FULL_SELECTION);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		managedForm.getToolkit().adapt(table);
		managedForm.getToolkit().paintBordersFor(table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
	}

}
