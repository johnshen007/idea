
import java.util.ArrayList;
        import java.util.List;

/**
 * 哈夫曼树
 * Created by Sheldon on 2019/4/11.
 * Project Name: alstudy.
 * Package Name: tree.
 */

// 结点结构
public  class Node{
    // 权值
    int value;
    // 左结点
    Node leftChild;
    // 右结点
    Node rightChild;

    public Node(int value){
        this.value = value;
    }
}

