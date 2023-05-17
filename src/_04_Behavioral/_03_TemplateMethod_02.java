package _04_Behavioral;

import java.util.ArrayList;

class MainTM2 {
    public static void main(String[] args) {
        String title = "디자인패턴";

        ArrayList<String> content = new ArrayList<String>();
        content.add("디자인패턴은 클래스 간의 효율적이고 최적화된 관계를 설계하는 것입니다.");
        content.add("각 패턴을 외우기 보다 이해하는 것이 중요합니다.");
        content.add("다양한 패턴을 접하고 반복적으로 이해할수록");
        content.add("각 디자인패턴에 대한 응용의 폭이 넓어질 것입니다.");

        String footer = "GIS Debeloper 김형준";

        Article article = new Article(title, content, footer);

        System.out.println("[CASE-1]");
        DisplayArticleTemplate style1 = new SimpleDisplayArticle(article);
        style1.display();

        System.out.println();

        System.out.println("[CASE-2]");
        DisplayArticleTemplate style2 = new CaptionDisplayArticle(article);
        style2.display();

    }
}

class Article {
    private String title;
    private ArrayList<String> content;
    private String footer;

    public Article(String title, ArrayList<String> content, String footer){
        this.title = title;
        this.content = content;
        this.footer = footer;
    }

    public String getTitle(){
        return title;
    }

    public ArrayList<String> getContent(){
        return content;
    }

    public String getFooter(){
        return footer;
    }
}

abstract class DisplayArticleTemplate {
    protected Article article;

    public DisplayArticleTemplate(Article article){
        this.article = article;
    }

    public final void display() {
        title();
        content();
        footer();
    }

    protected abstract void title();
    protected abstract void content();
    protected abstract void footer();
}

class SimpleDisplayArticle extends DisplayArticleTemplate{

    public SimpleDisplayArticle(Article article) {
        super(article);
    }

    @Override
    protected void title() {
        System.out.println(article.getTitle());
    }

    @Override
    protected void content() {
        ArrayList<String> content = article.getContent();
        int cntLines = content.size();
        for (int i = 0; i < cntLines; i++) {
            System.out.println(content.get(i));
        }
    }

    @Override
    protected void footer() {
        System.out.println(article.getFooter());
    }
}

class CaptionDisplayArticle extends DisplayArticleTemplate {

    public CaptionDisplayArticle(Article article) {
        super(article);
    }

    @Override
    protected void title() {
        System.out.println("TITLE : " + article.getTitle());
    }

    @Override
    protected void content() {
        ArrayList<String> content = article.getContent();
        int cntLines = content.size();
        for (int i = 0; i < cntLines; i++) {
            System.out.println("    " + content.get(i));
        }
    }

    @Override
    protected void footer() {
        System.out.println("FOOTER : " + article.getFooter());
    }
}