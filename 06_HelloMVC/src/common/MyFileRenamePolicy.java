package common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File oldFile) {
		File newFile =null;
		do {
			long currentTime = System.currentTimeMillis();//현재시간을 가져옴
			SimpleDateFormat sdf = new SimpleDateFormat("yyyMMdd HHmmssSSS");
			int rndNum=(int)(Math.random()*10000);
			String oldName = oldFile.getName(); //사용자가 올린 파일명
			String ext ="";
			int dot = oldName.lastIndexOf("."); //확장자를 떼어내는 작업
			if(dot>-1) {
				ext = oldName.substring(dot);
			}
			String  newName = sdf.format(new Date(currentTime))+"_"+rndNum+ext;
			//oldFile.getParent() : 부모디렉토리 경로
			//newName : 새로만들어진 파일이름
			newFile= new File(oldFile.getParent(), newName);
			
		}while(!createNewFile(newFile));
		
		return newFile;
	}
	public boolean createNewFile(File f)
	{
		try {
			//파일이 있으면 false, 파일이 없으면 true로 생성!
			return f.createNewFile();
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
