package com.showka.everpub.system;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class FileManager {

    /**
     * ファイルセパレータ.
     */
    private static final String fs = File.separator;
    /**
     * ファイルルートのパス.
     */
    @Value("${everpub.file.root}")
    private String root;

    /**
     * ファイル取得.
     *
     * <pre>
     * propertiesでファイル格納フォルダのパスを設定している場合、そこからの相対パスでファイルを取得する。
     * ファイルが無ければシステムエラー。
     * </pre>
     *
     * @param pathFromRoot ファイル格納フォルダからの相対パス
     * @return ファイル
     */
    public File get(String pathFromRoot) {
        String absolutePath = this.getAbsolutePath(pathFromRoot);
        File file = new File(absolutePath);
        if (!file.exists()) {
            throw new RuntimeException("ファイルが存在しません。: " + absolutePath);
        }
        return file;
    }

    /**
     * ファイル作成.
     *
     * @param pathFromRoot ファイル格納フォルダからの相対パス
     * @return ファイル
     */
    public File create(String pathFromRoot) {
        String absolutePath = this.getAbsolutePath(pathFromRoot);
        File file = new File(absolutePath);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("ファイル作成失敗。: " + absolutePath, e);
        }
        return file;
    }

    /**
     * ファイルパス取得.
     *
     * @param pathFromRoot ファイル格納フォルダからの相対パス
     * @return ファイルパス
     */
    private String getAbsolutePath(String pathFromRoot) {
        if (root == null || root.length() == 0) {
            throw new RuntimeException("ファイル格納フォルダのルートパスを設定してください。");
        }
        // ファイルの相対パス
        String _fsReg = fs.equals("\\") ? "\\\\" : fs;
        String _path = pathFromRoot.replaceAll("/", _fsReg);
        _path = _path.replaceAll("^" + _fsReg, "");
        // フルパス
        String _root = root.endsWith(_fsReg) ? root : root + fs;
        return _root + _path;
    }
}
