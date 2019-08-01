
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="dtGroupView")
@ViewScoped
public class GroupView implements Serializable {
//     
//    private final static String[] manufacturers;
//    private List<Sale> sales;
//     
//    private final static String[] playerNames;
//    private List<Integer> years;
//    private List<Player> players;
//     
//    static {        
//        manufacturers = new String[10];
//        manufacturers[0] = "Apple";
//        manufacturers[1] = "Samsung";
//        manufacturers[2] = "Microsoft";
//        manufacturers[3] = "Philips";
//        manufacturers[4] = "Sony";
//        manufacturers[5] = "LG";
//        manufacturers[6] = "Sharp";
//        manufacturers[7] = "Panasonic";
//        manufacturers[8] = "HTC";
//        manufacturers[9] = "Nokia";
//    }
//         
//    static {
//        playerNames = new String[10];
//        playerNames[0] = "Lionel Messi";
//        playerNames[1] = "Cristiano Ronaldo";
//        playerNames[2] = "Arjen Robben";
//        playerNames[3] = "Franck Ribery";
//        playerNames[4] = "Ronaldinho";
//        playerNames[5] = "Luis Suarez";
//        playerNames[6] = "Sergio Aguero";
//        playerNames[7] = "Zlatan Ibrahimovic";
//        playerNames[8] = "Neymar Jr";
//        playerNames[9] = "Andres Iniesta";
//    }
//         
//    @PostConstruct
//    public void init() {
//        sales = new ArrayList<Sale>();
//        for(int i = 0; i < 10; i++) {
//            sales.add(new Sale(manufacturers[i], getRandomAmount(), getRandomAmount(), getRandomPercentage(), getRandomPercentage()));
//        }
//         
//        years = new ArrayList<Integer>();
//        years.add(2010);
//        years.add(2011);
//        years.add(2012);
//        years.add(2013);
//        years.add(2014);
//         
//        players = new ArrayList<Player>();
//        for(int i = 0; i < 10; i++) {
//            players.add(new Player(playerNames[i], generateRandomGoalStatsData()));
//        }
//    }
// 
//    public List<Sale> getSales() {
//        return sales;
//    }
// 
//    private int getRandomAmount() {
//        return (int) (Math.random() * 100000);
//    }
// 
//    private int getRandomPercentage() {
//        return (int) (Math.random() * 100);
//    }
//     
//    public String getLastYearTotal() {
//        int total = 0;
// 
//        for(Sale sale : getSales()) {
//            total += sale.getLastYearSale();
//        }
// 
//        return new DecimalFormat("###,###.###").format(total);
//    }
// 
//    public String getThisYearTotal() {
//        int total = 0;
// 
//        for(Sale sale : getSales()) {
//            total += sale.getThisYearSale();
//        }
// 
//        return new DecimalFormat("###,###.###").format(total);
//    }
// 
//    public List<Integer> getYears() {
//        return years;
//    }
//     
//    public int getYearCount() {
//        return years.size();
//    }
// 
//    public List<Player> getPlayers() {
//        return players;
//    }
// 
//    private Map<Integer,Integer> generateRandomGoalStatsData() {
//        Map<Integer,Integer> stats = new LinkedHashMap<Integer, Integer>();
//        for (int i = 0; i < 5; i++) {
//            stats.put(years.get(i), getRandomGoals());
//        }
//         
//        return stats;
//    }
//     
//    private int getRandomGoals() {
//        return (int) (Math.random() * 50);
//    }
}