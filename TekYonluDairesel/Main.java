package TekYonluDairesel;

public class Main
{
    public static void main(String[] args)
    {
        
        linkedList liste = new linkedList();
        Main mainObj = new Main();

        /* ----------------------------------------------------------------- */
            // INSERT METHODU

//        mainObj.insert(liste, 1);
//        mainObj.print(liste);
//        System.out.println("---------");
//
//        mainObj.insert(liste, 7);
//        mainObj.print(liste);
//        System.out.println("---------");
//
//        mainObj.insert(liste, 5);
//        mainObj.print(liste);
//        System.out.println("---------");
//
//        mainObj.print(liste);

        /* ----------------------------------------------------------------- */
            // ORDERLYINSERT METHODU

        mainObj.orderlyInsert(liste,3);
        mainObj.print(liste);
        System.out.println("--------");

        mainObj.orderlyInsert(liste,5);
        mainObj.print(liste);
        System.out.println("---------");

        mainObj.orderlyInsert(liste,1);
        mainObj.print(liste);
        System.out.println("---------");

        mainObj.orderlyInsert(liste,7);
        mainObj.print(liste);
        System.out.println("---------");

        mainObj.orderlyInsert(liste,6);
        mainObj.print(liste);
        System.out.println("---------");

        System.out.println("orderlyInsert - Delete Gecisi");

        System.out.println("---------");

        /* ----------------------------------------------------------------- */
            // DELETE METHODU

        mainObj.delete(liste, 1); // rootu silmeye calisiyoruz
        mainObj.print(liste);
        System.out.println("---------");

        mainObj.delete(liste,42); // olmayan bi elemani silmeye calisiyoruz
        System.out.println("Liste:");
        mainObj.print(liste);
        System.out.println("---------");

        mainObj.delete(liste,6); // aradan eleman
        mainObj.print(liste);
        System.out.println("---------");

        mainObj.delete(liste,7); // sondaki eleman
        mainObj.print(liste);
        System.out.println("---------");

        mainObj.delete(liste,3); // tekrar root
        mainObj.print(liste);
        System.out.println("---------");

        mainObj.delete(liste,5); // son elemani sildik ( tekrar root)
        mainObj.print(liste); // liste bos (print) verecek
        System.out.println("---------");

        mainObj.delete(liste, 99); // listede olmayan bir eleman ancak!! liste zaten bos
        // Liste Bos(Delete Methodu)  hatasi verecek

        System.out.println(); // bosluk

        mainObj.print(liste); // liste bos(print) verecek
        System.out.println("---------");

        /* ----------------------------------------------------------------- */

    }
    void insert(linkedList liste, int data)
    {
        Node yeniDugum = new Node(data);
        if(liste.root == null) // eger liste bos ise
        {
            liste.root = yeniDugum; //root yap
            yeniDugum.next = yeniDugum; // root un sonraki elemanini root yap (dairesel)
        }
        else
        {
            Node iter = liste.root;
            while(iter.next != liste.root) // iterin nexti root degilse ilerlet
                iter = iter.next; // dairesel oldugu icin null demek yerine root dedik
            Node temp = iter.next;
            iter.next = yeniDugum;
            yeniDugum.next = temp;

            /*
             liste 1 5 3 olsun eklenecek eleman 2 olun
                iter 3te duruyor cunku nexti root
             Node temp = iter.next, 1 i temp de tutuyor

             iter.next = yeniDugum, 3 un nextini 2 yapıyor yani liste 1 5 3 2

             yeniDugum.next = temp, 2 nin nextini 1 yapiyor,
             -> yani listeyi dairesel hale getiriyor

             */
        }
    }
    void print(linkedList liste)
    {
        if(liste.root == null)
            System.out.println("liste bos(print methodu)");
        else
        {
            Node iter = liste.root;
            while(iter.next != liste.root) // ornek olarak liste 1 3 5 olsun
            {
                System.out.println(iter.data);
                iter=iter.next;
            }
            System.out.println(iter.data); // iter 5te kalir, 5in nexti 1'i(root) gosterdigi
            // icin iceri girmez. 5 i basmamis olur(son elemanin degeri)
            // o yuzden en son iter.data bastik
        }
    }
    void orderlyInsert(linkedList liste, int data)
    {
        Node yeniDugum = new Node(data);
        if(liste.root == null)
        {
            liste.root = yeniDugum;
            yeniDugum.next = liste.root;
        }
        else
        {
            if(yeniDugum.data < liste.root.data) // rootdan kucukse
            {  // 1 3 5
                Node iter = liste.root;
                while(iter.next != liste.root)
                    iter=iter.next;
                Node temp = iter.next;
                iter.next = yeniDugum;
                yeniDugum.next = temp;
                liste.root = yeniDugum;

                /*
                ustteki kod yerine (while in sonuna kadar ayni)

                iter.next = yeniDugum;
                yeniDugum.next = liste.root;
                liste.root = yeniDugum;

                de yazabilirsin

                ACIKLAMA:
                sadece root un oncesindeki sayiyi degisecegimiz icin ve
                roota her zaman liste.root diyerek erisebilecegimiz icin
                veri kaybetme olasiligimiz yok.

                Bu nedenle temp tutmamiz sart degil
                 */
            }
            else
            {
                Node iter = liste.root;
                while(iter.next != liste.root && iter.next.data < yeniDugum.data) //arada ya da sonda
                    iter = iter.next;
                Node temp = iter.next;
                iter.next = yeniDugum;
                yeniDugum.next = temp;

                /* ACIKLAMA KODU --- ustteki kodun aynisi

                if(iter.next == liste.root) // iterasyon sonda ise
                { ----1 3 5 e 7 eklemissin gibi dusun
                    Node temp = iter.next;   1 tempte
                    iter.next = yeniDugum;  5in nexti 7
                    yeniDugum.next = temp;  7nin nexti 1
                }
                else // arada bir yerde ise
                  ----1 3 5 7 ye 6 eklemissin gibi dusun
                { // iter 5 te durdu cunku iter.next.data > yeniDugum.data (7, 6dan kucuk degil; ilerlemedi)
                    Node temp = iter.next;
                    iter.next = yeniDugum;
                    yeniDugum.next = temp;
                }

                */
            }
        }

    }
    void delete(linkedList liste, int silinecekData)
    {
        Node silinecekDugum = new Node(silinecekData);

        if(liste.root == null)
            System.out.println("Liste Bos (Delete methodu)");

        else if(silinecekDugum.data == liste.root.data) // silinecek eleman root ise
        {
            if(liste.root.next == liste.root) // eger listede sadece ROOT kalmıs ise
            {
                liste.root.next = null; // gerekli mi emin degilim, olmasa da calisiyor gibi(?)
                liste.root = null;
            }
            else // listede root'dan baska eleman varsa
            {
                Node iter = liste.root; // listeyi dolasmak zorundayiz o yüzden iter olusturuyoruz
                while(iter.next != liste.root) // listenin son elemanina kadar ilerletiyoruz
                    iter=iter.next;
                iter.next = iter.next.next; // listeyi 1 3 5 gibi dusun, 5in nextini 3 yapiyoruz
                liste.root = iter.next; // 3 u de yeni root yapiyoruz
                /*
                 iter'in nexti artik 3u gosterdigi icin, iter.next dedigimizde otomatik olarak 3'un dugumu
                 liste.root = iter.next dedigimizde ise 3 u yeni root yapiyor

                 */
            }
        }
        else // eleman arada ise ya da yoksa
        {
            Node iter = liste.root;
            while(iter.next != liste.root && iter.next.data != silinecekDugum.data)
                iter=iter.next;
            if(iter.next == liste.root) // iter sonda ise (aranan elemana denk gelmedi)
                System.out.println("Silinmesi istenen eleman listede yoktur.");

            else // silinecek eleman arada bir yerde ornegin ... 5 6 7 , 6 silinecek
                iter.next = iter.next.next;
            // 5 in nexti artik 7

        }
    }

}
