package task1;


public class Main {
    public static void main(String[] args) {
        Cataloguer cataloguer = new Cataloguer();
        cataloguer.createCollection(new String[]{"/Users/vsevolodvisnevskij/Music/iTunes/iTunes Media/Music"});
        System.out.println(cataloguer.getCollection().toString());
        System.out.println(cataloguer.tagInfoDuplicatesToString());
        System.out.println(cataloguer.checkSumDuplicatesToString());

    }
}
