package ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import com.swtdesigner.SWTResourceManager;

import entity.UserInfo;
import entity.infoField.InfoField;
import entity.infoField.InfoFieldFactory;
import logiccenter.*;

public class SearchLocalDialog extends Dialog {
	Object result;
	private Text name;
	private Text nick;
	private Text cellphone;
	private Text text_3;
	private Text qq;
	private Text msn;
	private Text personalStatment;
	private Combo sex;
	private Button cancel;
	private DateTime dateTime;
	private Group registInfo;
	private Shell registDia;
	//private LogicCenter logicCenter;

	public SearchLocalDialog(Shell parent, int style) {
		super(parent, style);
	}

	public SearchLocalDialog(Shell parent) {
		this(parent, 0); // your default style bits go here (not the Shell's
		// style bits)
	}

	public Object open() {
		Shell parent = getParent();
		registDia = new Shell(parent, SWT.DIALOG_TRIM | SWT.MIN
				| SWT.APPLICATION_MODAL);
		registDia.setLayout(null);
		registDia.setText("\u672C\u5730\u641C\u7D22");
		{
			registInfo = new Group(registDia, SWT.NONE);
			registInfo.setFont(SWTResourceManager
					.getFont("΢���ź�", 8, SWT.NORMAL));
			registInfo.setLayout(null);
			registInfo.setBounds(10, 24, 574, 348);
			registInfo.setText("\u641C\u7D22\u8054\u7CFB\u4EBA");
			{
				Label label = new Label(registInfo, SWT.NONE);
				label
						.setFont(SWTResourceManager.getFont("΢���ź�", 8,
								SWT.NORMAL));
				label.setBounds(38, 34, 24, 17);
				label.setText("\u59D3\u540D");
			}
			{
				name = new Text(registInfo, SWT.BORDER);
				name.setBounds(86, 34, 142, 23);
			}
			{
				nick = new Text(registInfo, SWT.BORDER);
				nick.setBounds(363, 34, 142, 23);
			}
			{
				Label label = new Label(registInfo, SWT.NONE);
				label
						.setFont(SWTResourceManager.getFont("΢���ź�", 8,
								SWT.NORMAL));
				label.setBounds(314, 34, 24, 17);
				label.setText("\u6635\u79F0");
			}
			{
				Label label = new Label(registInfo, SWT.NONE);
				label
						.setFont(SWTResourceManager.getFont("΢���ź�", 8,
								SWT.NORMAL));
				label.setBounds(38, 115, 24, 17);
				label.setText("\u624B\u673A");
			}
			{
				cellphone = new Text(registInfo, SWT.BORDER);
				cellphone.setBounds(86, 112, 142, 23);
			}
			{
				Label label = new Label(registInfo, SWT.NONE);
				label
						.setFont(SWTResourceManager.getFont("΢���ź�", 8,
								SWT.NORMAL));
				label.setBounds(314, 74, 24, 17);
				label.setText("\u751F\u65E5");
			}
			{
				dateTime = new DateTime(registInfo, SWT.NONE);
				dateTime.setFont(SWTResourceManager.getFont("΢���ź�", 8,
						SWT.NORMAL));
				dateTime.setBounds(362, 72, 93, 24);
			}
			{
				Label label = new Label(registInfo, SWT.NONE);
				label.setFont(SWTResourceManager.getFont("΢���ź�", 8,
								SWT.NORMAL));
				label.setBounds(314, 115, 36, 17);
				label.setText("E-mail");
			}
			{
				text_3 = new Text(registInfo, SWT.BORDER);
				text_3.setBounds(363, 112, 142, 23);
			}
			{
				Label label = new Label(registInfo, SWT.NONE);
				label
						.setFont(SWTResourceManager.getFont("΢���ź�", 8,
								SWT.NORMAL));
				label.setBounds(38, 150, 24, 23);
				label.setText("QQ");
			}
			{
				qq = new Text(registInfo, SWT.BORDER);
				qq.setBounds(86, 150, 142, 23);
			}
			{
				Label label = new Label(registInfo, SWT.NONE);
				label
						.setFont(SWTResourceManager.getFont("΢���ź�", 8,
								SWT.NORMAL));
				label.setBounds(314, 150, 36, 17);
				label.setText("MSN");
			}
			{
				msn = new Text(registInfo, SWT.BORDER);
				msn.setBounds(362, 150, 142, 23);
			}
			{
				Label label = new Label(registInfo, SWT.NONE);
				label
						.setFont(SWTResourceManager.getFont("΢���ź�", 8,
								SWT.NORMAL));
				label.setBounds(38, 197, 48, 23);
				label.setText("\u4E2A\u4EBA\u63CF\u8FF0");
			}
			{
				personalStatment = new Text(registInfo, SWT.BORDER);
				personalStatment.setFont(SWTResourceManager.getFont("΢���ź�", 8,
						SWT.NORMAL));
				personalStatment.setBounds(98, 197, 427, 80);
			}
			{
				Label label = new Label(registInfo, SWT.NONE);
				label
						.setFont(SWTResourceManager.getFont("΢���ź�", 8,
								SWT.NORMAL));
				label.setBounds(38, 74, 24, 17);
				label.setText("\u6027\u522B");
			}
			{
				sex = new Combo(registInfo, SWT.READ_ONLY);
				sex.add("������");
				sex.add("��");
				sex.add("Ů");
				sex.setData("������", 0);
				sex.setData("��", 1);
				sex.setData("Ů", 2);
				sex.select(0);
				sex.setBounds(86, 71, 67, 21);
			}
		}
		{
			Button yes = new Button(registDia, SWT.NONE);
			yes.addSelectionListener(new SelectionAdapter() {
				private Tree tree;

				public void widgetSelected(SelectionEvent e) {
					registInfo.dispose();
					System.out.println("dispose");
					//gatherInfo();
					Group group = new Group(registDia, SWT.NONE);
					group.setBounds(10, 58, 430, 221);
					{
						tree = new Tree(group, SWT.BORDER);
						tree.setBounds(10, 10, 410, 201);
						{
							TreeItem treeItem = new TreeItem(tree, SWT.NONE);
							treeItem.setText("New TreeItem");
						}
					}
					
				}

				
			});
			yes.setFont(SWTResourceManager.getFont("΢���ź�", 8, SWT.NORMAL));
			yes.setBounds(404, 420, 68, 23);
			yes.setText("\u786E\u8BA4");
		}
		{
			cancel = new Button(registDia, SWT.NONE);
			cancel.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					cancel.getParent().dispose();
				}
			});
			cancel.setFont(SWTResourceManager.getFont("΢���ź�", 8, SWT.NORMAL));
			cancel.setBounds(498, 420, 68, 23);
			cancel.setText("\u53D6\u6D88");
		}
		// Your code goes here (widget creation, set result, etc).
		registDia.pack();
		registDia.open();
		Display display = parent.getDisplay();
		while (!registDia.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		return result;
	}
	public void setSearchResult(){
		
	}

}