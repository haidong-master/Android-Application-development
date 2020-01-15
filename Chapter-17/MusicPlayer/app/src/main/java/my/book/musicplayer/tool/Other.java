package my.book.musicplayer.tool;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

public class Other {


	public class ScanMusicFilenameFilter implements FilenameFilter {
		private static final String suffixs=".MP3.WMA.AAC.M4A";
		@Override
		public boolean accept(File dir, String filename) {
			if(suffixs.contains("."+Contsant.getSuffix(filename))){
				return true;
			}
			return false;
		}

	}
	/**
	 * �ļ�������
	 * */
	public class ScanMusicFilterFile implements FileFilter{

		@Override
		public boolean accept(File pathname) {
			if(pathname.isDirectory()&&pathname.canRead()){
				return pathname.list().length>0;
			}
			return false;
		}
	}
	
}
