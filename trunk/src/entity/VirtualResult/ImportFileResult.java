package entity.VirtualResult;

import algorithm.Checker;
import logiccenter.LogicCenter;
import entity.BoolInfo;

public class ImportFileResult extends OneTimeVirtualResult {
	private String fileName;
	private Checker fileNameChecker;//TODO 实现相关的检查类

	public ImportFileResult(String fileName, LogicCenter center) {
		super(center);
		this.fileName = fileName;
		//TODO 检查文件名：if (!fileNameChecker.check(fileName))...
		
		thread.start();
	}

	@Override
	protected BoolInfo getResult(){
		center.getDataCenter().importFile(fileName);
		//TODO 当前没有处理dataCenter的错误
		return new BoolInfo();
	}

}
