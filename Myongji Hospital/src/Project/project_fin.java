package Project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import Project.project_fin2.RoundedButton;
import Project.project_fin2.res_error;

class Patient{
	String ID;
	String PASSWD;
	String NAME;
	String RR_NUM;
	char GEN;
	String P_NUM;

	public String toString() {
		String gen;
		if (GEN == 'M') 
			gen = "남";
		else 
			gen = "여";
		return "ID: " + ID + '\n' + "NAME: " + NAME + '\n' + "GENDER: " + gen + '\n' + "PHONE NUMBER: " + P_NUM + '\n';
	}
	
	public String toString2() {
		return ID +"\t"+ PASSWD +"\t"+ NAME +"\t"+ RR_NUM+"\t" + P_NUM;
	}
	
	
}

class Professor {
	String ID;
	String PASSWD;
	String NAME;
	String SUB;
	String P_NUM;
}

class Patient_Reservation {
    String DATE;
    String DATETIME;
    String PA_NAME;
    String PA_RR_NUM;
    char PA_GEN;
    String PR_NUM;
    String PR_NAME;
    String PR_SUB;
    
    public String toString() {
    	return DATE + " " + PR_SUB + " " + PR_NAME + "교수 진찰 받음.\n";
    }
    public String toString2() {
    	String gen;
		if (PA_GEN == 'M') 
			gen = "남";
		else 
			gen = "여";
		return "이름: " + PA_NAME + '\n' + "주민등록번호: " + PA_RR_NUM + '\n' + "성별: " + gen + '\n' + "진료과목: " + PR_SUB + '\n' + "교수명: " + PR_NAME;
    }
}
class Date {
	String Year;
	String Month;
	String Day;
	String Time;
	
	public Date(String y, String m, String d, String t) {
		Year = y;
		Month = m;
		Day = d;
		Time = t;
	}
	public String toString() {
		return "년:" + Year + "월:" + Month + "일:" + Day + "시간:"+ Time;
	}
}


// 레이아웃 파이널과 같은 내용
public class project_fin extends JFrame {

	static Connection conn;
	static Vector<Patient> plist_pat;
	static Vector<Professor> plist_pro;
	static Vector<Patient_Reservation> plist_res;
	JList<Patient> cancel;

	Container c; // 컨테이너
	JFrame main_frame; // 현재 프레임
	CardLayout card; // 카드레이아웃

	// 다이얼로그
	change ch;
	right ri;
	res_error res_e;
	Font font = new Font("맑은 고딕", Font.BOLD, 12);
	Color cog = new Color(244, 244, 244); // 그레이
	Color cob = new Color(0, 0, 0);

	// 상단 라벨, 버튼 2개
	JPanel jp;
	JPanel logoline;
	ImageIcon ic;
	JLabel jic;
	JLabel la;
	JButton jb;
	JPanel btn;

	// 예약화면
	JPanel info;
	JPanel reservation_south;
	JPanel jla;
	JLabel lb1;JLabel lb2;JLabel lb3;JLabel lb4;
	JPanel jco;
	String name[];
	String dep[];
	String doc1[];String doc2[];String doc3[];String doc4[];String doc5[];String doc6[];String doc7[];
	String day[];
	String time[];
	JComboBox jc1;	JComboBox jc2;	JComboBox jc3;	JComboBox jc4;
	JButton ok;
	JLabel jla1;

	static String com_dep;
	   static String com_pro;
	   static String com_date="0000-00-00";
	   static String com_time; 


	
	// 로그인 전역변수
	private JButton btn1, btn2;
	PIDPW pidpw;
	EIDPW eidpw;
	CardLayout main_lay_card;
 	String id_tf;
 	String pw_tf;
	
 	JTextField ID;
    JPasswordField PW;
    JButton back, login;
    JLabel signup, find;
	   
	// 직원 전역변수
	JList<Patient> view_jl_list;
	    
	Container main_con;
	CardLayout main_cl;
	    
	JTextField RR_NUM;
	    
	JTextArea ta;
	JTextArea ta2;
    JScrollPane sc_ta;
    JComboBox Year;
    JComboBox Month;
    JComboBox Day;
    JComboBox Time;
    String[] year = {"2019","2020","2021","2022"};
    String[] month = {"01","02","03","04","05","06","07","08","09","10","11","12"};
    String[] day2 = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19",
             "20","21","22","23","24","25","26","27","28","29","30","31"};
    String[] time2 = {"10:00", "11:00", "14:00", "15:00", "16:00"};
 	
    String id;
    JTextArea ja;
    
	project_fin() {

		setSize(600, 400);
		setTitle("명지병원 진료 예약");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		c = this.getContentPane();
		card = new CardLayout();
		c.setLayout(card);

		// 로고 설정
		ImageIcon ic = new ImageIcon("./res/logo.png");
		setIconImage(ic.getImage());
		
		init();
		add(new Loginpage(),"Loginpage");
		add(new EIDPW(), "EIDPW");
		add(new PIDPW(),"PIDPW");
		add(new reservation(), "reservation");
		add(new change_and_cancel(), "change_and_cancel");
		add(new cancel_done(), "cancel_done");
		add(new Patient_records(),"Patient");
		add(new Check_schedule(),"Check");
		
		setResizable(false);
		setVisible(true);

	}

	
//서희 화면(로그인)
   class Loginpage extends JPanel {
        
         Loginpage() {
            
        	setTitle("명지병원");
            setLayout(new BorderLayout());
            
            //로고 이미지 설정 
            ImageIcon icon = new ImageIcon("./res/logo2.png");
            
            Image img = icon.getImage();
            Image changeImg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            ImageIcon changeIcon = new ImageIcon(changeImg);
            JLabel lb1 = new JLabel(changeIcon);
            add(lb1, BorderLayout.CENTER);
            
            ImageIcon img0 = new ImageIcon("./res/logo.png");
            setIconImage(img0.getImage());
            
            //패널 선언 
            JPanel center = new JPanel();
            center.setLayout(new FlowLayout(FlowLayout.CENTER));
            
            //여백 설정 
            center.setBorder(BorderFactory.createEmptyBorder(0,50,50,50));
            
            //고객 버튼 만들기 
            btn1 = new RoundedButton("고객");
            btn1.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
               card.show(c, "PIDPW");
               }
            });
            btn1.setPreferredSize(new Dimension(150,50));
            center.add(btn1);
            
            //직원 버튼 만들기 
            btn2 = new RoundedButton("직원");
            btn2.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
               card.show(c, "EIDPW");
               }
            });
            btn2.setPreferredSize(new Dimension(150,50));
            center.add(btn2);
            
            this.add(center, BorderLayout.SOUTH);
            
            setVisible(true);
         } 
   }
   
   //고객 로그인 클래스 
   class PIDPW extends JPanel {
         
	   
         PIDPW() {
            
        	 setTitle("명지병원");
             setSize(600,400);
             setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             
             //작은 로고 이미지 설정 
             ImageIcon icon = new ImageIcon("./res/logo.png");
             
             Image img = icon.getImage();
             Image changeImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
             ImageIcon changeIcon = new ImageIcon(changeImg);
             JLabel lb1 = new JLabel(changeIcon);
             lb1.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
             add(lb1, BorderLayout.NORTH);
              
             //아이디,패스워드 전체_패널 만들기  
             JPanel idpw_center = new JPanel();
             idpw_center.setLayout(new BorderLayout()); //이 보더레이아웃 안에 밑에 두 개의 플로우레이아웃이 존재함. 
             
             //아이디 패널 만들기
             JPanel id_top = new JPanel();
             id_top.setLayout(new FlowLayout());
             
             //아이디 설정 
             id_top.add(new JLabel("아이디    :"));
             
             ID = new JTextField(15);
             ID.setPreferredSize(new Dimension(100, 50)); //ID 텍스트필드 안에 여백 사이즈
             ID.setFont(new Font("", 0, 18)); //ID 필드의 글씨체, 두껍게&기울기, 폰트 사이즈 
             id_top.add(ID);
             
             idpw_center.add(id_top, BorderLayout.NORTH);
             
             //패스워드 패널 만들기 
             JPanel pw_btm = new JPanel();
             pw_btm.setLayout(new FlowLayout());
             
             //패스워드 설정  
             pw_btm.add(new JLabel("패스워드 :"));
             
             PW = new JPasswordField(15);
             PW.setPreferredSize(new Dimension(100, 50)); //PW 텍스트필드 안에 여백 사이즈
             PW.setFont(new Font("", 0, 18)); //PW 필드의 글씨체, 두껍게&기울기, 폰트 사이즈 
             pw_btm.setBorder(BorderFactory.createEmptyBorder(0,0,0,0)); //여백 크기, 아래에만 50의 여백을 줌. (위.왼.아래.오른)
             pw_btm.add(PW);
             
             idpw_center.add(pw_btm, BorderLayout.CENTER);
             
             //회원가입, 아이디&패스워드 찾기 패널 만들기 
             JPanel search = new JPanel();
             search.setLayout(new FlowLayout());
             search.setPreferredSize(new Dimension(1000,30)); //패널 크기 
             
             //회원가입, 아이디&패스워드 찾기 부분은 그냥 글씨만 나타내기(구현X) 
             signup = new JLabel("• 회원가입");
             signup.setPreferredSize(new Dimension(80,20));
             search.add(signup);
             
             find = new JLabel("• 아이디/비밀번호 찾기");
             find.setPreferredSize(new Dimension(125,20));
             search.add(find);
             
            search.setBorder(BorderFactory.createEmptyBorder(0,0,50,0)); //회원가입, 아이디&패스워드 찾기 패널 여백설정(0,0,50,0)
             
             idpw_center.add(search, BorderLayout.SOUTH);
             
             //뒤로가기, 로그인 버튼 패널 만들기 
             JPanel center = new JPanel();
             center.setLayout(new FlowLayout()); //양옆 간격 : 5 위아래 간격 : 0 으로 설정 
             center.setBorder(BorderFactory.createEmptyBorder(0,220,140,220)); //여백 크기(위, 왼, 아래, 오른)_80, 100, 130, 100
             center.setPreferredSize(new Dimension(1000,200)); //패널 크기
             
             //뒤로가기 버튼 만들기 설정 
             back = new RoundedButton("뒤로가기");
             back.addActionListener(new ActionListener() {
             
             @Override
             public void actionPerformed(ActionEvent e) {
                card.show(c, "Loginpage");
                
             	}
             });
             center.add(back);
             
             //고객 로그인 버튼 만들기 설정 
             login = new RoundedButton("고객 로그인");
             login.addActionListener(new ActionListener() {
             
                @Override
                public void actionPerformed(ActionEvent e) {

                 try {

                    Class.forName("com.mysql.cj.jdbc.Driver");
                     conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_project","root", "1234");
                    
                     id_tf = ID.getText();
                     pw_tf = PW.getText();
                    
                    Statement stm = conn.createStatement();

                    String sql = ("select * from Patient where ID = '"+id_tf+"'and PASSWD='"+pw_tf+"'");
                    ResultSet rs = stm.executeQuery(sql);
                    
                    if(rs.next()) {
                 	   
                 	  card.show(c, "reservation");
                       
                    } else {
                    	
                       //if id and password is wrong show message
                       JOptionPane.showMessageDialog(main_con, "아이디와 패스워드를 찾을 수 없습니다.", "알림창", JOptionPane.INFORMATION_MESSAGE);
                       ID.setText("");
                       PW.setText("");
                    }
                    
                 } catch(Exception ee) {
                    System.out.println(ee.getMessage());
                 }
              }
           });
          
             center.add(login);
             
             add(idpw_center, BorderLayout.CENTER);
             
             this.add(center, BorderLayout.SOUTH);

             setVisible(true);
          }
    }
   
   //직원 로그인 클래스
   class EIDPW extends JPanel {
         
	   private JTextField ID;
	   private JPasswordField PW;
	   private JButton back, login;
	   private JLabel signup, find;
         
         EIDPW() {
            
        	 setTitle("명지병원");
             setSize(600,400);
             setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             
             //작은 로고 이미지 설정 
             ImageIcon icon = new ImageIcon("./res/logo.png");
             
             Image img = icon.getImage();
             Image changeImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
             ImageIcon changeIcon = new ImageIcon(changeImg);
             JLabel lb1 = new JLabel(changeIcon);
             lb1.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
             add(lb1, BorderLayout.NORTH);
              
             //아이디,패스워드 전체_패널 만들기  
             JPanel idpw_center = new JPanel();
             idpw_center.setLayout(new BorderLayout()); //이 보더레이아웃 안에 밑에 두 개의 플로우레이아웃이 존재함. 
             
             //아이디 패널 만들기
             JPanel id_top = new JPanel();
             id_top.setLayout(new FlowLayout());
             
             //아이디 설정 
             id_top.add(new JLabel("아이디    :"));
             
             ID = new JTextField(15);
             ID.setPreferredSize(new Dimension(100, 50)); //ID 텍스트필드 안에 여백 사이즈
             ID.setFont(new Font("", 0, 18)); //ID 필드의 글씨체, 두껍게&기울기, 폰트 사이즈 
             id_top.add(ID);
             
             idpw_center.add(id_top, BorderLayout.NORTH);
             
             //패스워드 패널 만들기 
             JPanel pw_btm = new JPanel();
             pw_btm.setLayout(new FlowLayout());
             
             //패스워드 설정  
             pw_btm.add(new JLabel("패스워드 :"));
             
             PW = new JPasswordField(15);
             PW.setPreferredSize(new Dimension(100, 50)); //PW 텍스트필드 안에 여백 사이즈
             PW.setFont(new Font("", 0, 18)); //PW 필드의 글씨체, 두껍게&기울기, 폰트 사이즈 
             pw_btm.setBorder(BorderFactory.createEmptyBorder(0,0,0,0)); //여백 크기, 아래에만 50의 여백을 줌. (위.왼.아래.오른)
             pw_btm.add(PW);
             
             idpw_center.add(pw_btm, BorderLayout.CENTER);
             
             //회원가입, 아이디&패스워드 찾기 패널 만들기 
             JPanel search = new JPanel();
             search.setLayout(new FlowLayout());
             search.setPreferredSize(new Dimension(1000,30)); //패널 크기 
             
             //회원가입, 아이디&패스워드 찾기 부분은 그냥 글씨만 나타내기(구현X) 
             signup = new JLabel("• 회원가입");
             signup.setPreferredSize(new Dimension(80,20));
             search.add(signup);
             
             find = new JLabel("• 아이디/비밀번호 찾기");
             find.setPreferredSize(new Dimension(125,20));
             search.add(find);
             
             search.setBorder(BorderFactory.createEmptyBorder(0,0,50,0)); //회원가입, 아이디&패스워드 찾기 패널 여백설정(0,0,50,0)
             
             idpw_center.add(search, BorderLayout.SOUTH);
             
             //뒤로가기, 로그인 버튼 패널 만들기 
             JPanel center = new JPanel();
             center.setLayout(new FlowLayout()); //양옆 간격 : 5 위아래 간격 : 0 으로 설정 
             center.setBorder(BorderFactory.createEmptyBorder(0,220,140,220)); //여백 크기(위, 왼, 아래, 오른)_80, 100, 130, 100
             center.setPreferredSize(new Dimension(1000,200)); //패널 크기
             
             //뒤로가기 버튼 만들기 설정 
             back = new RoundedButton("뒤로가기");
             back.addActionListener(new ActionListener() {
             
             @Override
             public void actionPerformed(ActionEvent e) {
                card.show(c, "Loginpage");
                ID.setText("");
                PW.setText("");
             	}
             });
             center.add(back);
             
             //직원 로그인 버튼 만들기 설정 
             login = new RoundedButton("직원 로그인");
             login.addActionListener(new ActionListener() {
             
                @Override
                public void actionPerformed(ActionEvent e) {

                 try {

                    Class.forName("com.mysql.cj.jdbc.Driver");
                     conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_project","root", "1234");
                    
                    String id_tf = ID.getText();
                    String pw_tf = PW.getText();
                    
                    Statement stm = conn.createStatement();

                    String sql = ("select * from Professor where ID = '"+id_tf+"'and PASSWD='"+pw_tf+"'");
                    ResultSet rs = stm.executeQuery(sql);
                    
                    if(rs.next()) {
                 	   
                 	  card.show(c, "Check");
                       
                    } else {
                    	
                       //if id and password is wrong show message
                       JOptionPane.showMessageDialog(main_con, "아이디와 패스워드를 찾을 수 없습니다.", "알림창", JOptionPane.INFORMATION_MESSAGE);
                       
                    }
                    
                 } catch(Exception ee) {
                    System.out.println(ee.getMessage());
                 }
              }
           });
          
             center.add(login);
             
             add(idpw_center, BorderLayout.CENTER);
             
             this.add(center, BorderLayout.SOUTH);

             setVisible(true);
          }
    }

	// 예약 화면
	class reservation extends JPanel {

		public reservation() {
			setLayout(new BorderLayout());

			add(new lable_n_btn(), BorderLayout.NORTH);

			// 정보 패널 ( 라벨, 콤보박스 )
			info = new JPanel();
			info.setLayout(new BorderLayout());

			// 진료 예약 버튼 누를 시 밑에 뜨는 확인 , 취소 버튼 패널
			reservation_south = new JPanel();

			// 라벨패널, info 보더레이아웃에 저장
			jla = new JPanel();
			jla.setLayout(new GridLayout(2, 4));

			// 공백주기
			jla.add(new JLabel(""));jla.add(new JLabel(""));jla.add(new JLabel(""));jla.add(new JLabel(""));

			lb1 = new JLabel("진료과");
			lb1.setFont(font);
			jla.add(lb1);
			lb2 = new JLabel("교수");
			lb2.setFont(font);
			jla.add(lb2);
			lb3 = new JLabel("날짜");
			lb3.setFont(font);
			jla.add(lb3);
			lb4 = new JLabel("시간");
			lb4.setFont(font);
			jla.add(lb4);
			info.add(jla, BorderLayout.NORTH);

			// 콤보박스 패널, info 보더레이아웃에 저장
			jco = new JPanel();
			jco.setLayout(new FlowLayout());

			dep = new String[] { "정형/신경외과", "내과", "일반외과", "안과", "소아과", "산과", "피부과" };
			doc1 = new String[] { "양단아", "계승철" };
			doc2 = new String[] { "김경아", "이형묵" };
			doc3 = new String[] { "김영로" };
			doc4 = new String[] { "조범석" };
			doc5 = new String[] { "안유정" };
			doc6 = new String[] { "김현호" };
			doc7 = new String[] { "심한뫼" };

			String day[] = { "2022-12-01", "2022-12-02", "2022-12-03", "2022-12-04", "2022-12-05", "2022-12-06",
					"2022-12-07", "2022-12-08", "2022-12-09", "2022-12-10", "2022-12-11", "2022-12-12", "2022-12-13",
					"2022-12-14", "2022-12-15", "2022-12-16", "2022-12-17", "2022-12-18", "2022-12-19", "2022-12-20",
					"2022-12-21", "2022-12-22", "2022-12-23", "2022-12-24", "2022-12-25", "2022-12-26", "2022-12-27",
					"2022-12-28", "2022-12-29", "2022-12-30", "2022-12-31" };

			String time[] = { "10:00", "11:00",  "14:00", "15:00", "16:00" };

			jc1 = new JComboBox(dep);
			jc1.setPreferredSize(new Dimension(140, 50));
			jc1.setFont(font);
			jc1.setBackground(cog);
			jc1.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
				
					if (jc1.getSelectedIndex() == 1) {
						 jc2.setModel(new DefaultComboBoxModel(doc2));
					}
					else if (jc1.getSelectedIndex() == 2) {
						 jc2.setModel(new DefaultComboBoxModel(doc3));
					}
					else if (jc1.getSelectedIndex() == 3) {
						 jc2.setModel(new DefaultComboBoxModel(doc4));
					}
					else if (jc1.getSelectedIndex() == 4) {
						 jc2.setModel(new DefaultComboBoxModel(doc5));
					}
					else if (jc1.getSelectedIndex() == 5) {
						 jc2.setModel(new DefaultComboBoxModel(doc6));
					}
					else if (jc1.getSelectedIndex() == 6) {
						 jc2.setModel(new DefaultComboBoxModel(doc7));
					}

				}
			});
			jco.add(jc1);

			jc2 = new JComboBox(doc1);
			jc2.setPreferredSize(new Dimension(140, 50));
			jc2.setFont(font);
			jc2.setBackground(cog);
			jco.add(jc2);

			jc3 = new JComboBox(day);
			jc3.setPreferredSize(new Dimension(140, 50));
			jc3.setFont(font);
			jc3.setBackground(cog);
			jco.add(jc3);

			jc4 = new JComboBox(time);
			jc4.setPreferredSize(new Dimension(140, 50));
			jc4.setFont(font);
			jc4.setBackground(cog);
			jco.add(jc4);
			info.add(jco, BorderLayout.CENTER);

			add(info, BorderLayout.CENTER);
			add(reservation_south, BorderLayout.SOUTH);

			
			
			// Dialog
			ri = new right();

			// 진료 예약 버튼 누를 시 뜨는 확인, 취소 버튼
			ok = new RoundedButton("예약 하기");
			ok.setPreferredSize(new Dimension(80, 50));
			ok.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
				
					// 콤보박스 선택시 값 들어가기
		               com_dep = jc1.getSelectedItem().toString();
		               com_pro = jc2.getSelectedItem().toString();
		               com_date = jc3.getSelectedItem().toString();
		               com_time = jc4.getSelectedItem().toString();
		               
					
					ri.setVisible(true);
				}
			});

			reservation_south.add(ok);

		}
	}

	// 진료예약 누르면 나오는 예약확인 버튼 팝업창
	class right extends JDialog {
			
		right() {
            setSize(300, 200);
            setLayout(new BorderLayout());

            JLabel ja = new JLabel("예약 하시겠습니까?");
            ja.setFont(font);
            ja.setHorizontalAlignment(JLabel.CENTER);
            add(ja, JLabel.CENTER);

            
            // 에러 다이얼로그
            res_e = new res_error();
            
            JPanel y_or_n = new JPanel();
            y_or_n.setLayout(new FlowLayout());
            RoundedButton yes = new RoundedButton("네");
            yes.addActionListener(new ActionListener() {

               @Override
               public void actionPerformed(ActionEvent e) {
                  
                  // 예약 팝업 확인 시 환자 예약 쿼리
                  Patient pat = new Patient();
                  pat.NAME = jc2.getSelectedItem().toString();

                  try {
                     
                     Patient p = new Patient();
                     
                     String id = id_tf;
                     
                     String pro_name = com_pro;
                     
                     Statement stm = conn.createStatement();
                     
                     // 분명 db로 연결해서 할 수 있는 방법 있을 텐데 못 찾았음
                     if(com_pro.equals("양단아")) com_pro="102872";
                     if(com_pro.equals("김경아")) com_pro="232627";
                     if(com_pro.equals("계승철")) com_pro="117062";
                     if(com_pro.equals("김영로")) com_pro="321012";
                     if(com_pro.equals("조범석")) com_pro="415022";
                     if(com_pro.equals("이형묵")) com_pro="275302";
                     if(com_pro.equals("안유정")) com_pro="632209";
                     if(com_pro.equals("김현호")) com_pro="102872";
                     if(com_pro.equals("심한뫼")) com_pro="972938";
                     
                     
                     ResultSet srs = stm.executeQuery("SELECT * FROM patient WHERE id= '"+id+"'");
                     
                     while(srs.next()) {
                        p.NAME = srs.getString("name");
                        p.RR_NUM = srs.getString("rr_num");
                        p.GEN = srs.getString("GEN").charAt(0);
                     }
                     
                     
                     String q = "insert into Patient_Reservation values('"+ com_date+"','"+com_time+"','"+p.NAME+"','"+p.RR_NUM+"','"+p.GEN+"','"+com_pro+"','"+pro_name+"','"+com_dep +"')";
                     
                     System.out.println(q);
                     int r = stm.executeUpdate(q);// 쿼리를 날림
                     if (r > 0)
                        System.out.println("insert문 성공");
                     
                    

                  } catch (Exception ee) {
                    ee.printStackTrace();
                	  ri.setVisible(false);
                     res_e.setVisible(true);
                  } 

                  ri.setVisible(false);
                  card.show(c, "change_and_cancel");
               }
            });
            RoundedButton no = new RoundedButton("아니오");
            no.addActionListener(new ActionListener() {

               @Override
               public void actionPerformed(ActionEvent e) {
                  ri.setVisible(false);

               }
            });
            y_or_n.add(yes);
            y_or_n.add(no);

            add(y_or_n, BorderLayout.SOUTH);

         }
      }
	
	// 예약 변경 취소 화면
	class change_and_cancel extends JPanel {

		change_and_cancel() {
			setLayout(new BorderLayout());
			
			JPanel jp = new JPanel();
			jp.setLayout(new BorderLayout());
			jp.add(new lable_n_btn(), BorderLayout.NORTH);
			
			
			RoundedButton jb = new RoundedButton("조회");
			
			jb.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					 
					
					try {
						
						Patient p = new Patient();
						Patient_Reservation p2 = new Patient_Reservation();
						JLabel jla1;
						Statement stm = conn.createStatement();
						String id = id_tf;
						ResultSet srs = stm.executeQuery("SELECT * FROM patient WHERE id= '"+id+"'");
						
						System.out.println(id);

						while(srs.next()) {
							p.NAME = srs.getString("name");
							p.RR_NUM = srs.getString("rr_num");
							p.GEN = srs.getString("GEN").charAt(0);
							
							
						}
						ResultSet srs2 = stm.executeQuery("SELECT * FROM Patient_Reservation WHERE date= '"+com_date+"'");
						while(srs2.next()) {
							p2.DATE = srs2.getString("date");
							p2.DATETIME = srs2.getString("DATETIME");
						}
						
						
						// 예약한 내용 출력할 코드	
						 ja = new JTextArea(8,30);
						String re_info = "\n 날짜 : "+p2.DATE+"\n 시간 :"+ p2.DATETIME + "으로 예약 되었습니다.";
						System.out.println(re_info);
						//jla1 = new JLabel(re_info);
						ja.setFont(font);
						ja.setText(re_info);
						//jla1.setHorizontalAlignment(JLabel.CENTER);
						jp.add(ja, BorderLayout.SOUTH);
						jp.updateUI();
						
						
						
					} catch (Exception ee) {
						ee.printStackTrace();
					}
				}
			});
			
			jp.add(jb,BorderLayout.CENTER);
			add(jp,BorderLayout.NORTH);
			
			
	
			// 변경/취소 버튼 누를 시 밑에 뜨는 변경, 취소 버튼 패널
			JPanel ccs = new JPanel();
			ccs.setLayout(new FlowLayout());

			// Dialog
			ch = new change();

			// 변경/취소 누를 시 뜨는 변경, 취소 버튼
			RoundedButton cha = new RoundedButton("예약변경");
			cha.setPreferredSize(new Dimension(280, 50));
			cha.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					ch.setVisible(true);
				}
			});
			RoundedButton can = new RoundedButton("예약취소");
			can.setPreferredSize(new Dimension(280, 50));
			can.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					Patient p = new Patient();
					
					try {
						
						Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
						String id = id_tf;
						ResultSet srs = stm.executeQuery("SELECT * FROM patient WHERE id= '"+id+"'");
						
						while(srs.next()) {
							p.NAME = srs.getString("name");
						}
						int srs1 = stm.executeUpdate("delete from Patient_Reservation where date ='"+ com_date+"'and pa_name ='"+p.NAME+"'");
						System.out.println(com_date);
						if(srs1>0) System.out.println("예약 삭제 성공");
						else System.out.println("예약 삭제 오류");
						ja.setText("");
						
						
						
					} catch (Exception ee) {
						ee.printStackTrace();
					}
					
					//jla1.setText("");
					card.show(c, "cancel_done");
				}
			});

			ccs.add(cha);
			ccs.add(can);
			add(ccs, BorderLayout.SOUTH);

		}
	}

	// 변경/취소 버튼 누를 떄 나오는 변경 버튼 팝업	
	class change extends JDialog {

		change() {
			setSize(300, 200);
			setLayout(new BorderLayout());

			JLabel ja = new JLabel("예약을 변경 하시겠습니까?");
			ja.setFont(font);
			ja.setHorizontalAlignment(JLabel.CENTER);
			add(ja, JLabel.CENTER);
		
			JPanel ok_or_cancel = new JPanel();
			ok_or_cancel.setLayout(new FlowLayout());
			RoundedButton ok = new RoundedButton("네");
			ok.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					Patient p = new Patient();
					
					try {
						
						Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
						String id = id_tf;
						ResultSet srs = stm.executeQuery("SELECT * FROM patient WHERE id= '"+id+"'");
						
						while(srs.next()) {
							p.NAME = srs.getString("name");
						}
						int srs1 = stm.executeUpdate("delete from Patient_Reservation where date ='"+ com_date+"'and pa_name ='"+p.NAME+"'");
						System.out.println(com_date);
						if(srs1>0) System.out.println("변경 전 삭제 성공");
						else System.out.println("변경 전 삭제 오류");


					} catch (Exception ee) {
						ee.printStackTrace();
					}

					ch.setVisible(false);
					card.show(c, "reservation");

				}
			});
			RoundedButton cancel = new RoundedButton("아니오");
			cancel.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					ch.setVisible(false);
				}
			});
			ok_or_cancel.add(ok);
			ok_or_cancel.add(cancel);

			add(ok_or_cancel, BorderLayout.SOUTH);

		}
	}

	// 예약 오류 다이얼로그 
	class res_error extends JDialog {

		res_error() {
			setSize(300, 200);
			setLayout(new BorderLayout());

			JLabel ja = new JLabel("예약이 불가능한 시간입니다. 다시 선택해 주세요.");
			ja.setFont(font);
			ja.setHorizontalAlignment(JLabel.CENTER);
			add(ja, JLabel.CENTER);
		

		}
	}
	
	// 예약 취소 완료 화면
	class cancel_done extends JPanel {

		cancel_done() {
			setLayout(new BorderLayout());

			add(new lable_n_btn(), BorderLayout.NORTH);

			JLabel info = new JLabel("예약 취소 되었습니다", JLabel.CENTER);
			info.setFont(font);
			info.setHorizontalAlignment(JLabel.CENTER);
		

		add(info, BorderLayout.CENTER);

		}

	}
	
	// 위에 라벨, 버튼2개 클래스
	class lable_n_btn extends JPanel {

		lable_n_btn() {

			// 라벨 버튼 합칠 패널
			jp = new JPanel();
			jp.setLayout(new BorderLayout());

			// 라벨 왼똑 패널
			logoline = new JPanel();
			logoline.setLayout(new BorderLayout());
			//logoline.setBackground(lablebag);
			ic = new ImageIcon("./res/logo_lable.png");
			jic = new JLabel(ic);
			//la = new JLabel("명지병원");
			//la.setForeground(Color.white);
			logoline.add(jic, BorderLayout.WEST);
			//logoline.add(la, BorderLayout.CENTER);
			jb = new JButton("로그아웃");
			jb.setFont(font);
			jb.setFocusPainted(false);
	         jb.setContentAreaFilled(false);
	         jb.setBorderPainted(false);
			jb.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					card.show(c, "Loginpage");
					ID.setText("");
					PW.setText("");
				
				}
			});
			logoline.add(jb, BorderLayout.EAST);
			jp.add(logoline, BorderLayout.NORTH);

			// 위에 2개 버튼 패널
			btn = new JPanel();
			btn.setLayout(new FlowLayout());

			// 진료 예약 버튼
			RoundedButton jb1 = new RoundedButton("진료 예약");
			jb1.setPreferredSize(new Dimension(290, 50));
			jb1.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					card.show(c, "reservation");

				}
			});

			// 예약변경, 취소 버튼
			RoundedButton jb2 = new RoundedButton("예약 변경/취소");
			jb2.setPreferredSize(new Dimension(290, 50));
			jb2.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					card.show(c, "change_and_cancel");

				}
			});

			btn.add(jb1);
			btn.add(jb2);
			jp.add(btn, BorderLayout.CENTER);
			add(jp, BorderLayout.NORTH);
		}
	}

	// 환자 조회
	class Patient_records extends JPanel {
		   JTextArea ta2;
		   
	       Patient_records() {
	         setLayout(new FlowLayout());
	         
	         JPanel top = new JPanel();//상단 패널 
	         top.setLayout(new BorderLayout());
	         ImageIcon logo3 = new ImageIcon("./res/logo_lable.png");
	         JLabel lb = new JLabel(logo3);
	         top.add(lb);
	         
	         JButton logout = new JButton("로그아웃");
	         logout.setFont(font);
	         logout.setFocusPainted(false);
	         logout.setContentAreaFilled(false);
	         logout.setBorderPainted(false);
	         logout.addActionListener(new ActionListener() {
	 			
	 			@Override
	 			public void actionPerformed(ActionEvent e) {
	 				card.show(c, "Loginpage");
	 				ID.setText("");
					PW.setText("");
	 			}
	 		 });
	         top.add(logout, BorderLayout.EAST);
	         add(top);
	         
	         JPanel btn = new JPanel(); //버튼패널 
	         btn.setLayout(new FlowLayout());
	         RoundedButton cs = new RoundedButton("예약 조회");
	         cs.setPreferredSize(new Dimension(280, 50));
	         cs.setFocusPainted(false);
	         cs.setContentAreaFilled(false);
	         cs.setBackground(new Color(0,0,0));
	         cs.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	               // TODO Auto-generated method stub
	            	ta2.setText("");
	            	 card.show(c, "Check");
	            	 
	            }
	         });
	         btn.add(cs);
	         
	         RoundedButton pr = new RoundedButton("환자 정보 조회");
	         pr.setPreferredSize(new Dimension(280, 50));
	         pr.setFocusPainted(false);
	         pr.setContentAreaFilled(false);
	         pr.setBackground(new Color(0,0,0));
	         pr.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	               // TODO Auto-generated method stub
	            	card.show(c, "Patient");
	               ta.setText("");
	            }
	         });
	         btn.add(pr);
	         
	         add(btn, BorderLayout.NORTH);
	         
	         JPanel jp = new JPanel(new FlowLayout());
	         ta2 = new JTextArea(10,45);
	         ta2.setFont(font);
	         JScrollPane scrollPane = new JScrollPane(ta2);
	         scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	         scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			 jp.add(scrollPane);
			 
			 JPanel search = new JPanel();
	            search.setLayout(new FlowLayout());
	            JLabel lb5 = new JLabel("[환자의 주민번호를 입력하세요(-포함)]   ");
	            lb5.setFont(font);
	            search.add(lb5);
	            RR_NUM = new JTextField(10);
	            search.add(RR_NUM);
	         RoundedButton search_btn = new RoundedButton("검색");
	         
	         search_btn.setFocusPainted(false);
	         search_btn.setContentAreaFilled(false);
	         search_btn.setBackground(new Color(0,0,0));
	         search_btn.addActionListener(new ActionListener() {
	        	 
	             @Override
	             public void actionPerformed(ActionEvent e) {
	                // TODO Auto-generated method stub
	                //데이터 가져와서 출력 
	            	 String text2 = "";
	            	 String input = RR_NUM.getText(); 
	            	 try {
	            		 Statement sts = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	            	     String query = "SELECT * FROM team_project.Patient_Reservation WHERE PA_RR_NUM = '"+input+"'";
	            	     ResultSet Patient_Reservation = sts.executeQuery(query);
	            	         
	            		 while(Patient_Reservation.next()) {
	            		     Patient_Reservation RV = new Patient_Reservation();
	            		     RV.DATE =Patient_Reservation.getString("DATE");
	            		     RV.PR_NAME = Patient_Reservation.getString("PR_NAME");
	            		     RV.PR_SUB = Patient_Reservation.getString("PR_SUB");
	            		            
	            		     text2 += RV.toString();
	            		     
	            		     for(int j = 0; j < plist_pat.size(); j++) {
	                    		 String inputRR_NUM = plist_pat.get(j).RR_NUM;
	                    		 if(inputRR_NUM.equals(input)) {
	                    			 if(j>= 0) {
	                    				 String text = "[환자정보]\n" + plist_pat.get(j).toString() + '\n';
	                    				 ta2.setText(text);
	                    	         }
	                    	     }
	            		     }
	            		     ta2.append("[진료내역]\n" + text2);
	            		 }
	            		 Patient_Reservation.close();
	            		 sts.close();
	            		 
	            	 } catch (Exception ee) {
	            		 ee.printStackTrace();
	            	 }
	            	 
	            	 RR_NUM.setText("");
	             }
	             
	          });
	          
		      search.add(search_btn);
		      add(search);
		      add(jp);
		      
	        }
	   }
	
	// 스케줄 확인
	class Check_schedule extends JPanel {
		  
	      Check_schedule() {
	         setLayout(new FlowLayout());
	         
	         JPanel top = new JPanel();//상단 패널 
	         top.setLayout(new BorderLayout());
	         ImageIcon logo3 = new ImageIcon("./res/logo_lable.png");
	         JLabel lb = new JLabel(logo3);
	         top.add(lb);
	         
	         JButton logout = new JButton("로그아웃");
	         logout.setFont(font);
	         logout.setFocusPainted(false);
	         logout.setContentAreaFilled(false);
	         logout.setBorderPainted(false);
	         logout.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					card.show(c, "Loginpage");
					ID.setText("");
					PW.setText("");
				}
			 });
	         top.add(logout, BorderLayout.EAST);
	         add(top);
	         
	         JPanel btn = new JPanel(); //버튼패널 
	         btn.setLayout(new FlowLayout());
	         RoundedButton cs = new RoundedButton("예약 조회");
	         cs.setPreferredSize(new Dimension(280, 50));
	         cs.setFocusPainted(false);
	         cs.setContentAreaFilled(false);
	         cs.setBackground(new Color(255,255,255));
	         cs.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	               // TODO Auto-generated method stub
	               card.show(c, "Check");
	               ta2.setText("");
	            }
	         });
	         btn.add(cs);
	         
	         RoundedButton pr = new RoundedButton("환자 정보 조회");
	         pr.setPreferredSize(new Dimension(280, 50));
	         pr.setFocusPainted(false);
	         pr.setContentAreaFilled(false);
	         pr.setBackground(new Color(0,0,0));
	         pr.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	               // TODO Auto-generated method stub
	               card.show(c, "Patient");
	               ta.setText("");
	            }
	         });
	         btn.add(pr);
	         
	         add(btn);
	         
	         JPanel date = new JPanel(); //예약 날짜 시간 
	         date.setLayout(new FlowLayout());
	         
	         JLabel lb1 = new JLabel("년도: ");
	            lb1.setFont(font);
	            date.add(lb1);
	            Year = new JComboBox(year);
	            Year.setFont(font);
	            Year.setBackground(cog);
	            date.add(Year);
	            
	            JLabel lb2 = new JLabel("월: ");
	            lb2.setFont(font);
	            date.add(lb2);
	            Month = new JComboBox(month);
	            Month.setFont(font);
	            Month.setBackground(cog);
	            date.add(Month);
	            
	            JLabel lb3 = new JLabel("일: ");
	            lb3.setFont(font);
	            date.add(lb3);
	            Day = new JComboBox(day2);
	            Day.setFont(font);
	            Day.setBackground(cog);
	            date.add(Day);
	            
	            JLabel lb4 = new JLabel("시간: ");
	            lb4.setFont(font);
	            date.add(lb4);
	            Time = new JComboBox(time2);
	            Time.setFont(font);
	            Time.setBackground(cog);
	            date.add(Time);
	         
	         ta = new JTextArea(13,45);
	         
	         RoundedButton search = new RoundedButton("검색");
	         search.setFocusPainted(false);
	         search.setContentAreaFilled(false);
	         search.setBackground(new Color(0,0,0));
	         search.addActionListener(new ActionListener() {
	            
	            @Override
	            public void actionPerformed(ActionEvent e) {
	               // TODO Auto-generated method stub
	            	String jc_y = Year.getSelectedItem().toString();
	                String jc_m = Month.getSelectedItem().toString();
	                String jc_d = Day.getSelectedItem().toString();
	                String jc_date = jc_y + "-" + jc_m + "-" + jc_d;
	                String jc_t = Time.getSelectedItem().toString();
	                String text;
	                
	            	 try {
	            		 Statement sts = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	            	     String query = "SELECT * FROM team_project.Patient_Reservation WHERE DATE = '"+jc_date+"' and  DATETIME = '"+jc_t+"'";
	            	     ResultSet Patient_Reservation = sts.executeQuery(query);
	            	         
	            		 while(Patient_Reservation.next()) {
	            		     Patient_Reservation R = new Patient_Reservation();
	            		     R.PA_NAME =Patient_Reservation.getString("PA_NAME");
	            		     R.PA_RR_NUM = Patient_Reservation.getString("PA_RR_NUM");
	            		     R.PA_GEN = Patient_Reservation.getString("PA_GEN").charAt(0);
	            		     R.PR_NAME = Patient_Reservation.getString("PR_NAME");
	            		     R.PR_SUB = Patient_Reservation.getString("PR_SUB");
	            		     text = R.toString2();
	            		     
	            		     ta.setText(text);
	            		 }
	            		 Patient_Reservation.close();
	            		 sts.close();
	            	 } catch (Exception ee) {
	            		 ee.printStackTrace();
	            	 }
	            	
	            }
	         });
	         date.add(search);
	         add(date);
	         add(ta);
	      }
	   }
	
	// 자바 둥근 버튼
	public class RoundedButton extends JButton {
		public RoundedButton() {
			super();
			decorate();
		}

		public RoundedButton(String text) {
			super(text);
			decorate();
		}

		public RoundedButton(Action action) {
			super(action);
			decorate();
		}

		public RoundedButton(Icon icon) {
			super(icon);
			decorate();
		}

		public RoundedButton(String text, Icon icon) {
			super(text, icon);
			decorate();
		}

		protected void decorate() {
			setBorderPainted(false);
			setOpaque(false);
		}

		@Override
		protected void paintComponent(Graphics g) {
			Color c = new Color(204, 220, 237); // 배경색 결정
			Color o = new Color(0, 0, 0); // 글자색 결정
			int width = getWidth();
			int height = getHeight();
			Graphics2D graphics = (Graphics2D) g;
			graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			if (getModel().isArmed()) {
				graphics.setColor(c.darker());
			} else if (getModel().isRollover()) {
				graphics.setColor(c.brighter());
			} else {
				graphics.setColor(c);
			}
			graphics.fillRoundRect(0, 0, width, height, 10, 10);
			FontMetrics fontMetrics = graphics.getFontMetrics();
			Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds();
			int textX = (width - stringBounds.width) / 2;
			int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent();
			graphics.setColor(o);
			graphics.setFont(font);
			graphics.drawString(getText(), textX, textY);
			graphics.dispose();
			super.paintComponent(g);
		}
	}

	// init
	void init() {
		   Statement sts;
		   Statement sts2;
		   Statement sts3;
		   plist_pat.removeAll(plist_pat);
		   plist_pro.removeAll(plist_pro);
		   plist_res.removeAll(plist_res);
		      
		   try {
			   sts = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		       ResultSet Patient = sts.executeQuery("SELECT * FROM team_project.Patient");
		          
		       while(Patient.next()) {
		    	   Patient P = new Patient();
		           P.ID = Patient.getString("ID");
		           P.PASSWD = Patient.getString("PASSWD");
		           P.NAME = Patient.getString("NAME");
		           P.RR_NUM = Patient.getString("RR_NUM");
		           P.GEN = Patient.getString("GEN").charAt(0);
		           P.P_NUM= Patient.getString("P_NUM");
		           plist_pat.add(P);
		       }
		       Patient.close();
		       sts.close();
		   } catch (SQLException e) {
		        e.printStackTrace();
		   }
		   
		   try {
		       sts2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		       ResultSet Professor = sts2.executeQuery("SELECT * FROM team_project.Professor");
		          
		       while(Professor.next()) {
		    	   Professor PR = new Professor();
		           PR.ID = Professor.getString("ID");
		           PR.PASSWD = Professor.getString("PASSWD");
		           PR.NAME = Professor.getString("NAME");
		           PR.SUB = Professor.getString("SUB");
		           PR.P_NUM= Professor.getString("P_NUM");
		           plist_pro.add(PR);
		       }
		       Professor.close();
		       sts2.close();
		   	} catch (Exception e) {
		   		 e.printStackTrace();
		   	}
		   
		   	try {
		   		sts3 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		   		ResultSet Patient_Reservation = sts3.executeQuery("SELECT * FROM team_project.Patient_Reservation");
	         
		   		while(Patient_Reservation.next()) {
		        	Patient_Reservation RV = new Patient_Reservation();
		            RV.DATE =Patient_Reservation.getString("DATE");
		            RV.DATETIME = Patient_Reservation.getString("DATETIME");
		            RV.PA_RR_NUM = Patient_Reservation.getString("PA_RR_NUM");
		            RV.PA_GEN = Patient_Reservation.getString("PA_GEN").charAt(0);
		            RV.PR_NUM = Patient_Reservation.getString("PR_NUM");
		            RV.PR_NAME = Patient_Reservation.getString("PR_NAME");
		            RV.PR_SUB = Patient_Reservation.getString("PR_SUB");
		            plist_res.add(RV);
		   		}
		   		Patient_Reservation.close();
		   		sts3.close();
		   	} catch (Exception e) {
		   		e.printStackTrace();
		   	}
	   }
	
	
	public static void main(String[] args) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "1234");
			System.out.println("DB 연결 완료");
			
			// 데이터 초기화
			plist_pat = new Vector<Patient>();
			plist_res = new Vector<Patient_Reservation>();
			plist_pro = new Vector<Professor>();
			
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("DB 연결 오류");
		}

		new project_fin();
	}
}
