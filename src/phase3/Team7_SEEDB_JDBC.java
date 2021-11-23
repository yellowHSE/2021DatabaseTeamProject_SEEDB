package phase3;

import java.sql.*; // import JDBC package
import java.util.Scanner;
import java.io.Closeable;
import java.io.IOException;

public class Team7_SEEDB_JDBC {

	public static final String URL = "jdbc:oracle:thin:@192.168.56.1:1521:orcl";
	public static final String USER_SEED = "seed";
	public static final String USER_PASSWD = "seed";

	static String now_USER_ID;
	static String now_USER_PW;
	static int now_USER_Type = 1; /* ���� ������ �з�: 1) �� 2) ��� 3) ������ . �⺻���� �� */

	/*
	 * public static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	 * public static final String USER_SEED = "teamproject"; public static final
	 * String USER_PASSWD = "comp322";
	 */

	static Scanner keyboard = new Scanner(System.in);

	public static void main(String[] args) {
		/***************************************************
		 * ����
		 ***************************************************/
		Connection conn = null; // Connection object
		Statement stmt = null; // Statement object

		try {
			// Load a JDBC driver for Oracle DBMS
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Get a Connection object
			System.out.println("Success!");
		} catch (ClassNotFoundException e) {
			System.err.println("error = " + e.getMessage());
			System.exit(1);
		}

		// Make a connection
		try {
			conn = DriverManager.getConnection(URL, USER_SEED, USER_PASSWD);
			System.out.println("Connected.");
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.err.println("Cannot get a connection: " + ex.getLocalizedMessage());
			System.err.println("Cannot get a connection: " + ex.getMessage());
			System.exit(1);
		}

		try {
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
		} catch (SQLException ex2) {
			System.err.println("sql error = " + ex2.getMessage());
			System.exit(1);
		}

		/***************************************************
		 * SEEDB ���α׷�
		 ***************************************************/

		while (true) {
			System.out.println("SEEDB�� ���� ���� ȯ���մϴ�!\n\n");

			/* �α���, ȸ������ �޴� ��� */
			System.out.println("-----------------------------------");
			System.out.println("---------------�޴�-----------------");
			System.out.println("[1] �α���       [2] ȸ������         [3] ���α׷� ����");
			System.out.println("-----------------------------------");
			System.out.println("-----------------------------------");

			/* �޴� �Է¹ޱ� */
			int menu_num, Login_Result;
			System.out.printf("<�޴��� �������ּ���>: ");
			menu_num = keyboard.nextInt();

			if (menu_num == 1) {
				Login_Result = Login(conn, stmt);

				/* �α��ο� ���� */
				if (Login_Result == 1) {
					int select_menu;
					switch (now_USER_Type) {
					/* 1) ����: �� */
					case 1:
						while (true) {
							/* �� ���� ȭ�� */
							System.out.println("-----------------------------------");
							System.out.println("---------------�޴�-----------------");
							System.out.println("[1] ���� ����    [2] �ֹ� ���  [3] ��������");
							System.out.println("[4] Ŀ�´�Ƽ   [5] ����������  [6] �α׾ƿ�");
							System.out.println("-----------------------------------");
							System.out.println("-----------------------------------");
							System.out.println("�޴��� �����ϼ���: ");

							select_menu = keyboard.nextInt();

							/* �α׾ƿ� */
							if (select_menu == 6)
								break;

							/* �� �޴� ���� */
							switch (select_menu) {
							/* ��: [1] ���� ���� */
							case 1: {
								orderoption(conn, stmt);
								break;
							}

							/* ��: [2] �ֹ� ��� */
							case 2: {
								myOrderList(conn, stmt);
								break;
							}

							/* ��: [3] �������� */
							case 3:
								break;

							/* ��: [4] Ŀ�´�Ƽ */
							case 4:
								break;

							/* ��: [5] ���������� */
							case 5:
								Mypage(conn, stmt);
								break;
							}

						} // �� �޴� while�� ����
						System.out.println("[������ ������ �����մϴ�.]");
						break;

					/* 2) ����: ��� */
					case 2:
						while (true) {
							/* �Ǹ� ��� ���� ȭ�� */
							System.out.println("-----------------------------------");
							System.out.println("---------------�޴�-----------------");
							System.out.println("[1] ����      [2] �ֹ� ���  [3] ��������");
							System.out.println("[4] Ŀ�´�Ƽ   [5] ����������  [6] �α׾ƿ�");
							System.out.println("-----------------------------------");
							System.out.println("-----------------------------------");
							System.out.println("�޴��� �����ϼ���: ");

							select_menu = keyboard.nextInt();

							/* �α׾ƿ� */
							if (select_menu == 6)
								break;

							/* �Ǹ� ��� �޴� ���� */
							switch (select_menu) {
							/* �Ǹ� ���: [1] ���� */
							case 1:
								break;

							/* �Ǹ� ���: [2] �ֹ� ��� */
							case 2:
								OrgOrderList(conn, stmt);
								break;

							/* �Ǹ� ���: [3] �������� */
							case 3:
								break;

							/* �Ǹ� ���: [4] Ŀ�´�Ƽ */
							case 4:
								break;

							/* �Ǹ� ���: [5] ���������� */
							case 5:
								Mypage(conn, stmt);
								break;
							}
						}
						System.out.println("[�Ǹ� ������� ������ �����մϴ�.]");
						break;

					/* 3) ����: ������ */
					case 3:
						while (true) {
							/* ������ ���� ȭ�� */
							System.out.println("-----------------------------------");
							System.out.println("---------------�޴�-----------------");
							System.out.println("[1] ȸ��/��� ����  [2] �ֹ����  [3] ��������");
							System.out.println("[4] Ŀ�´�Ƽ   [5] ����������  [6] �α׾ƿ�");
							System.out.println("-----------------------------------");
							System.out.println("-----------------------------------");
							System.out.println("�޴��� �����ϼ���: ");

							select_menu = keyboard.nextInt();

							/* �α׾ƿ� */
							if (select_menu == 6)
								break;

							/* ������ �޴� ���� */
							switch (select_menu) {
							/* ������: [1] ȸ��/��� ���� */
							case 1:
								break;

							/* ������: [2] �ֹ� ��� */
							case 2:
								manOrderList(conn, stmt);
								break;

							/* ��: [3] �������� */
							case 3: {
								System.out.println("-----------------------------------");
								System.out.println("---------------�޴�-----------------");
								System.out.println("[1] �Խñ� �б�  ");

								NBread(conn, stmt);

								break;
							}

							/* ��: [4] Ŀ�´�Ƽ */
							case 4: {
								System.out.println("-----------------------------------");
								System.out.println("---------------�޴�-----------------");
								System.out.println("[1] �Խñ� ����    [2] �Խñ� �˻�  [3] �Խñ� �����");
								System.out.println("-----------------------------------");
								System.out.println("-----------------------------------");
								System.out.println("�޴��� �����ϼ���: ");
								int select_menu1 = keyboard.nextInt();

								switch (select_menu1) {
								case 1:
									BBwrite(conn, stmt);
									break;
								case 2:
									BBread(conn, stmt);
									break;
								case 3:
									BBdelete(conn, stmt);
									break;

								}

							}
							/* ������: [5] ���������� */
							case 5:
								Mypage(conn, stmt);
								break;
							}
						}
						System.out.println("[�����ڴ��� ������ �����մϴ�.]");
						break;
					}
				}
			} else if (menu_num == 2)
				Sign_up(conn, stmt);
			else if (menu_num == 3)
				break;

		}

		System.out.println("SEEDB�� �����մϴ�. �̿����ּż� �����մϴ�.\n");
		/***************************************************
		 * ����
		 ***************************************************/
		// Release database resources.

		try {
			// Close the Statement object.
			if (stmt != null)
				stmt.close();
			// Close the Connection object.
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} // main ����

	/* �α��� �Լ� */
	public static int Login(Connection conn, Statement stmt) {
		int result = 0;
		String ID, PW, sql;

		int Login_menu_num;
		System.out.println("[1] �α����� �����ϼ̽��ϴ�!\n\n");
		System.out.println("-----------------------------------");
		System.out.println("�α����ϰ��� �ϴ� ������ �����Ͻʽÿ�.");
		System.out.println("---------------�޴�-----------------");
		System.out.println("[1] �����/������       [2] �Ǹ� ���");
		System.out.println("-----------------------------------");
		System.out.println("-----------------------------------");

		/* �޴� �Է¹ޱ� */
		System.out.printf("<�α��� �޴��� �������ּ���>: ");
		Login_menu_num = keyboard.nextInt();

		switch (Login_menu_num) {
		case 1:
			System.out.println("[1] �����/�����ڸ� �����ϼ̽��ϴ�!\n\n");

			int User_id_wrong = 0;
			int User_pw_wrong = 0;
			while (true) {
				/* 3��° ���� �� ���� ȭ������ ��ȯ */
				if (User_id_wrong > 1 || User_pw_wrong > 1) {
					System.out.println("�α��ο� �����Ͽ����ϴ�!\n\n\n");
					result = 0;
					break;
				}
				System.out.println("���̵�� ��й�ȣ�� �Է����ּ���.\n\n");
				System.out.printf("<���̵�>: ");
				ID = keyboard.next();
				System.out.printf("<��й�ȣ>: ");
				PW = keyboard.next();
				try {
					sql = "SELECT U.USERID, U.PW \r\n" + "FROM \"USER\" U \r\n" + "WHERE U.USERID = '" + ID + "'";
					ResultSet rs = stmt.executeQuery(sql);
					int count = 0;
					while (true) {
						count++;
						if (!(rs.next())) {
							if (count == 1) {
								System.out.println("�߸��� ���̵��̰ų� ��ϵ��� ���� ���̵��Դϴ�.");
								User_id_wrong++;
							}
							count = 0;
							break;
						}

						String real_id = rs.getString(1);
						String real_pw = rs.getString(2);

						if (real_pw.equals(PW)) {
							/* �����ڰ� ������ �� �� */
							if (real_id.contains("manager"))
								now_USER_Type = 3;

							System.out.printf("%s �� �ݰ����ϴ�!\n", ID);
							now_USER_ID = real_id;
							now_USER_PW = real_pw;
							result = 1;
							return result;
						} else {
							System.out.println("�߸��� ��й�ȣ�Դϴ�.\n");
							User_pw_wrong++;
						}
					}
					conn.commit();
				} catch (SQLException ex2) {
					System.err.println("sql error = " + ex2.getMessage());
					System.exit(1);
				}
			}
			break;
		/* [2] �Ǹ� ��� */
		case 2:
			System.out.println("[2] �����/�����ڸ� �����ϼ̽��ϴ�!\n\n");

			int Org_id_wrong = 0;
			int Org_pw_wrong = 0;
			while (true) {
				/* 3��° ���� �� ���� ȭ������ ��ȯ */
				if (Org_id_wrong > 1 || Org_pw_wrong > 1) {
					System.out.println("�α��ο� �����Ͽ����ϴ�!");
					result = 0;
					break;
				}
				System.out.println("��� �̸��� ��й�ȣ�� �Է����ּ���.\n\n");
				System.out.printf("<��� �̸�>: ");
				ID = keyboard.next();
				System.out.printf("<��й�ȣ>: ");
				PW = keyboard.next();
				try {
					sql = "SELECT O.ORGNAME, O.ORG_PW \r\n" + "FROM ORGANIZATION O \r\n" + "WHERE O.ORGNAME = '" + ID
							+ "'";
					ResultSet rs = stmt.executeQuery(sql);
					int count = 0;
					while (true) {
						count++;
						if (!(rs.next())) {
							if (count == 1) {
								System.out.println("�߸��� ��� �̸��̰ų� ��ϵ��� ���� ����Դϴ�.");
								Org_id_wrong++;
							}
							count = 0;
							break;
						}

						String real_id = rs.getString(1);
						String real_pw = rs.getString(2);

						if (real_pw.equals(PW)) {
							/* �����ڰ� ��� �� �� */
							now_USER_Type = 2;
							System.out.printf("%s �� �ݰ����ϴ�!\n", ID);
							now_USER_ID = real_id;
							result = 1;
							return result;
						} else {
							System.out.println("�߸��� ��й�ȣ�Դϴ�.\n");
							Org_pw_wrong++;
						}
					}
					conn.commit();
				} catch (SQLException ex2) {
					System.err.println("sql error = " + ex2.getMessage());
					System.exit(1);
				}
			}
			break;
		}
		return result;
	}// �α��� �Լ� ����

	/* ȸ������ �Լ� */
	public static void Sign_up(Connection conn, Statement stmt) {
		String sql;
		int SignUp_menu_num, res;

		System.out.println("[2] ȸ�������� �����ϼ̽��ϴ�!\n\n");

		System.out.println("-----------------------------------");
		System.out.println("ȸ�������ϰ��� �ϴ� ������ �����Ͻʽÿ�.");
		System.out.println("---------------�޴�-----------------");
		System.out.println("[1] �����       [2] �Ǹ� ���");
		System.out.println("-----------------------------------");
		System.out.println("-----------------------------------");

		/* �޴� �Է¹ޱ� */
		System.out.printf("<ȸ������ �޴��� �������ּ���>: ");
		SignUp_menu_num = keyboard.nextInt();

		switch (SignUp_menu_num) {
		/* ȸ������: [1] �����/������ */
		case 1:
			System.out.println("ȸ������: [1] ����ڸ� �����ϼ̽��ϴ�!\n\n");

			String iUser_Id = null, iUser_Name, iUser_Pw, iUser_Addr, iUser_Age, iUser_PhoneN, iUser_Gender,
					iUser_Email;
			int iUser_wrong_num = 0;

			System.out.println("ȸ�������� �����մϴ�. manager�� �������� �ʴ� id�� �ۼ����ֻ���.\n");
			while (true) {
				if (iUser_wrong_num > 1) {
					System.out.println("ȸ�����Կ� �����Ͽ����ϴ�. \n\n");
					return;
				}
				System.out.printf("<���̵�>: ");
				iUser_Id = keyboard.next();

				if (iUser_Id.contains("manager")) {
					System.out.printf("id�� manager�� ������ �� �����ϴ�. �ٽ� �Է��� �ּ���.\n\n");
					continue;
				}

				try {
					sql = "SELECT U.USERID \r\n" + "FROM \"USER\" U \r\n" + "WHERE U.USERID = '" + iUser_Id + "'";
					ResultSet rs = stmt.executeQuery(sql);
					if (!(rs.next())) {
						System.out.println("��� ������ ���̵��Դϴ�.");
						break;
					} else {
						iUser_wrong_num++;
						System.out.println("�̹� �����ϴ� ���̵��Դϴ�.");
					}

				} catch (SQLException ex2) {
					System.err.println("sql error = " + ex2.getMessage());
					System.exit(1);
				}
			}

			System.out.printf("<����>: ");
			iUser_Name = keyboard.next();

			System.out.printf("<��й�ȣ>: ");
			iUser_Pw = keyboard.next();

			System.out.printf("<�ּ�>: ");
			iUser_Addr = keyboard.next();

			System.out.printf("<����>: ");
			iUser_Age = keyboard.next();

			System.out.printf("<��ȭ��ȣ>: 010-xxxx-xxxx �������� �Է��� �ּ���: ");
			iUser_PhoneN = keyboard.next();

			System.out.printf("<����>: F(����) �Ǵ� M(����)�� �Է��� �ּ���: ");
			iUser_Gender = keyboard.next();

			System.out.printf("<�̸��� �ּ�>: ");
			iUser_Email = keyboard.next();

			try {
				sql = "INSERT INTO \"USER\" VALUES (" + "'" + iUser_Id + "', " + "'" + iUser_Name + "', " + "'"
						+ iUser_Pw + "', " + "'" + iUser_Addr + "', " + iUser_Age + ", " + iUser_PhoneN + ", " + "'"
						+ iUser_Gender + "'" + "'" + iUser_Email + "')";
				res = stmt.executeUpdate(sql);
				if (res == 1) {
					System.out.println("ȸ�����Կ� �����Ͽ����ϴ�.\n");
					conn.commit();
					return;
				} else {
					System.out.println("ȸ�����Կ� �����Ͽ����ϴ�.\n");
					return;
				}
			} catch (SQLException ex2) {
				System.err.println("sql error = " + ex2.getMessage());
				System.exit(1);
			}
			break;
		/* ȸ������: [2] �Ǹ� ��� */
		case 2:
			System.out.println("ȸ������: [2] �Ǹ� ����� �����ϼ̽��ϴ�!\n\n");
			String iOrgName = null, iOrg_Purpose, iOrg_Region, iOrg_AffiliateDate, iOrg_AffiliatePeriod, iOrg_PW;
			int iOrg_wrong_num = 0;

			System.out.println("ȸ�������� �����մϴ�. ��� �̸��� �ۼ����ֻ���.\n");
			while (true) {
				if (iOrg_wrong_num > 1) {
					System.out.println("ȸ�����Կ� �����Ͽ����ϴ�. \n\n");
					return;
				}
				System.out.printf("<����̸�>: ");
				iOrgName = keyboard.next();

				try {
					sql = "SELECT O.ORGNAME \r\n" + "FROM ORGANIZATION O \r\n" + "WHERE O.ORGNAME = '" + iOrgName + "'";
					ResultSet rs = stmt.executeQuery(sql);
					if (!(rs.next())) {
						System.out.println("��� ������ ����Դϴ�.");
						break;
					} else {
						iOrg_wrong_num++;
						System.out.println("�̹� �����ϴ� ����Դϴ�.");
					}

				} catch (SQLException ex2) {
					System.err.println("sql error = " + ex2.getMessage());
					System.exit(1);
				}
			}

			System.out.printf("<��� ����: L(�뿩) �Ǵ� S(�Ǹ�)�� �Է����ּ���.>: ");
			iOrg_Purpose = keyboard.next();

			System.out.printf("<����>: ");
			iOrg_Region = keyboard.next();

			System.out.printf("<���޽��� ��¥: yyyy-mm-dd �������� �Է����ּ���. ex) 2021-03-01>: ");
			iOrg_AffiliateDate = keyboard.next();

			System.out.printf("<���ޱⰣ: �� ���� �Է����ּ���. ex) 365>: ");
			iOrg_AffiliatePeriod = keyboard.next();

			System.out.printf("<��й�ȣ>: ");
			iOrg_PW = keyboard.next();

			try {
				sql = "INSERT INTO ORGANIZATION VALUES (" + "'" + iOrgName + "', " + "'" + iOrg_Purpose + "', " + "'"
						+ iOrg_Region + "', " + "'" + iOrg_AffiliateDate + "', " + iOrg_AffiliatePeriod + ", " + "'"
						+ iOrg_PW + "')";
				res = stmt.executeUpdate(sql);
				if (res == 1) {
					System.out.println("ȸ�����Կ� �����Ͽ����ϴ�.\n");
					conn.commit();
					return;
				} else {
					System.out.println("ȸ�����Կ� �����Ͽ����ϴ�.\n");
					return;
				}
			} catch (SQLException ex2) {
				System.err.println("sql error = " + ex2.getMessage());
				System.exit(1);
			}
			break;
		} // ȸ������ �з� switch�� ����
	}// ȸ������ �Լ� ����

	public static void orderoption(Connection conn, Statement stmt) {
		System.out.println("���� �ֹ� ����� �������ּ���.");
		System.out.println("[1] �̸����� �˻��ϱ�  [2] �뵵�� ���� �˻��ϱ�  [3] �α� ������ ���� �˻��ϱ�");
		String OrderMenu = keyboard.next();

		if (OrderMenu.equalsIgnoreCase("1")) {
			System.out.println("\r\n'[1] �̸����� �˻��ϱ�'�� �����ϼ̽��ϴ�!\r\n");

			// ���� ��ü ��� ���
			try {
				String sql = "SELECT SeedName, VarietyName, H.OrgName, Org_Purpose\r\n"
						+ "FROM \"SEED\" S INNER JOIN HAS H ON S.VarietyID = H.VarietyID INNER JOIN \"ORGANIZATION\" ORG ON H.OrgName = ORG.OrgName";
				ResultSet rs = stmt.executeQuery(sql);
				System.out.println("SEEDNAME | VARIETYNAME | ORGNAME | ORG_PURPOSE");
				System.out.println("------------------------------------");
				while (rs.next()) {
					String outputSeedName = rs.getString(1);
					String outputVarietyName = rs.getString(2);
					String outputOrgName = rs.getString(3);
					String outputOrgPurpose = rs.getString(4);
					if (outputOrgPurpose.equalsIgnoreCase("L"))
						outputOrgPurpose = "�뿩";
					else if (outputOrgPurpose.equalsIgnoreCase("S"))
						outputOrgPurpose = "�Ǹ�";
					System.out.println(outputSeedName + " | " + outputVarietyName + " | " + outputOrgName + " | "
							+ outputOrgPurpose);
				}
			} catch (SQLException ex2) {
				System.err.println("sql error = " + ex2.getMessage());
				System.exit(1);
			}

			ordering(conn, stmt);
		} else if (OrderMenu.equalsIgnoreCase("2")) {
			System.out.println("\r\n'[2] �뵵�� ���� �˻��ϱ�'�� �����ϼ̽��ϴ�!\r\n");
			int orgcnt = 0;

			// ���� �뵵 ���
			try {
				String sql = "SELECT DISTINCT S.SeedPurPose \r\n" + "FROM SEED S \r\n";
				ResultSet rs = stmt.executeQuery(sql);
				System.out.println("���� �뵵");
				System.out.println("------------------------------------");
				while (rs.next()) {
					String outputSeedPurPose = rs.getString(1);
					System.out.println(outputSeedPurPose);
				}
			} catch (SQLException ex2) {
				System.err.println("sql error = " + ex2.getMessage());
				System.exit(1);
			}

			String inputSeedPurPose = "";
			inputSeedPurPose = keyboard.next(); // ���� �뵵 �Է¹ޱ�
			// �뵵�� ���� ���� ���
			try {
				String sql = "SELECT SeedName, VarietyName, H.OrgName, Org_Purpose\r\n"
						+ "FROM \"SEED\" S INNER JOIN HAS H ON S.VarietyID = H.VarietyID INNER JOIN \"ORGANIZATION\" ORG ON H.OrgName = ORG.OrgName\r\n"
						+ "WHERE S.SeedPurpose = '" + inputSeedPurPose + "'";
				ResultSet rs = stmt.executeQuery(sql);
				System.out.println("SEEDNAME | VARIETYNAME | ORGNAME | ORG_PURPOSE");
				System.out.println("-----------------------------------------------");
				while (rs.next()) {
					String outputSeedName = rs.getString(1);
					String outputVarietyName = rs.getString(2);
					String outputOrgName = rs.getString(3);
					String outputOrgPurpose = rs.getString(4);
					if (outputOrgPurpose.equalsIgnoreCase("L"))
						outputOrgPurpose = "�뿩";
					else if (outputOrgPurpose.equalsIgnoreCase("S"))
						outputOrgPurpose = "�Ǹ�";
					System.out.println(outputSeedName + " | " + outputVarietyName + " | " + outputOrgName + " | "
							+ outputOrgPurpose);
					orgcnt++;
				}
				if (orgcnt == 0) {
					System.out.println("�˼��մϴ�. '" + inputSeedPurPose + "' ������ ������ ����� �����ϴ�.");
					return;
				}
			} catch (SQLException ex2) {
				System.err.println("sql error = " + ex2.getMessage());
				System.exit(1);
			}
			ordering(conn, stmt);
		}

		else if (OrderMenu.equalsIgnoreCase("3")) {
			System.out.println("\r\n[3] �α������ ���� �˻��ϱ⸦ �����ϼ̽��ϴ�!\r\n");
			System.out.println("[1] ���̺� �α� ����  [2] �Ⱓ�� �α� ����");
			int topMenu = keyboard.nextInt();
			String VarietyName = "";
			String SeedName = "";

			if (topMenu == 1) {
				System.out.println("\r\n'[1] ���̺� �α������ ���� �˻��ϱ�'�� �����ϼ̽��ϴ�!\r\n");
				System.out.println("	[1] û�ҳ�  [2] û��  [3] �߳�  [4] ���");
				// varietyID�� �Է�
				int ageMenu = keyboard.nextInt();
				if (ageMenu == 1) {
					System.out.println("\r\n'[1] û�ҳ� �α���� �˻��ϱ�'�� �����ϼ̽��ϴ�!\r\n");
					try {
						// ǰ�� ��ȣ ���
						String sql = "SELECT\r\n"
								+ "DENSE_RANK() OVER (ORDER BY VARIETYCOUNT DESC, Total_QUANTITY DESC) ORDER_COUNT, TEEN_SEEDNAME AS SEEDNAME, TEEN_VARIETYNAME AS VARIETYNAME\r\n"
								+ "FROM(\r\n"
								+ "     SELECT VarietyName AS TEEN_VARIETYNAME, SeedName AS TEEN_SEEDNAME, COUNT(*) AS VARIETYCOUNT, SUM(Quantity) AS Total_QUANTITY\r\n"
								+ "     FROM (\r\n"
								+ "           SELECT OD_UserID, OD_VarietyID, Age, Quantity, SeedName, VarietyName\r\n"
								+ "           FROM (\r\n"
								+ "                 SELECT OD.OD_USERID, OD.OD_VarietyID, AGE, OD.Quantity, S.SeedName, S.VarietyName\r\n"
								+ "                 FROM \"USER\" U\r\n"
								+ "                 INNER JOIN \"ORDER\" OD ON U.UserID = OD.OD_UserID\r\n"
								+ "                 FULL OUTER JOIN SEED S ON OD.OD_VarietyID = S.VarietyID)\r\n"
								+ "           WHERE Age < 20)\r\n"
								+ "     GROUP BY OD_VarietyID, VarietyName, SeedName\r\n"
								+ "     ORDER BY COUNT(OD_VarietyID) DESC)";
						ResultSet rs = stmt.executeQuery(sql);
						System.out.println("���� |   ���� �̸�   |  ǰ�� �̸�");
						System.out.println("-----------------------------------");
						while (rs.next()) {
							String OrderCount = rs.getString(1);
							SeedName = rs.getString(2);
							VarietyName = rs.getString(3);
							System.out.println(OrderCount + " | " + SeedName + " | " + VarietyName);
						}
					} catch (SQLException ex2) {
						System.err.println("sql error = " + ex2.getMessage());
						System.exit(1);
					}
					topOrdering(conn, stmt);
					ordering(conn, stmt);
				} else if (ageMenu == 2) {
					System.out.println("\r\n'[2] û�� �α���� �˻��ϱ�'�� �����ϼ̽��ϴ�!\r\n");
					try {
						// ǰ�� ��ȣ ���
						String sql = "SELECT\r\n"
								+ "DENSE_RANK() OVER (ORDER BY VARIETYCOUNT DESC, Total_QUANTITY DESC) ORDER_COUNT, TEEN_SEEDNAME AS SEEDNAME, TEEN_VARIETYNAME AS VARIETYNAME\r\n"
								+ "FROM(\r\n"
								+ "     SELECT VarietyName AS TEEN_VARIETYNAME, SeedName AS TEEN_SEEDNAME, COUNT(*) AS VARIETYCOUNT, SUM(Quantity) AS Total_QUANTITY\r\n"
								+ "     FROM (\r\n"
								+ "           SELECT OD_UserID, OD_VarietyID, Age, Quantity, SeedName, VarietyName\r\n"
								+ "           FROM (\r\n"
								+ "                 SELECT OD.OD_USERID, OD.OD_VarietyID, AGE, OD.Quantity, S.SeedName, S.VarietyName\r\n"
								+ "                 FROM \"USER\" U\r\n"
								+ "                 INNER JOIN \"ORDER\" OD ON U.UserID = OD.OD_UserID\r\n"
								+ "                 FULL OUTER JOIN SEED S ON OD.OD_VarietyID = S.VarietyID)\r\n"
								+ "           WHERE 20 <= Age AND AGE < 31)\r\n"
								+ "     GROUP BY OD_VarietyID, VarietyName, SeedName\r\n"
								+ "     ORDER BY COUNT(OD_VarietyID) DESC)";
						ResultSet rs = stmt.executeQuery(sql);
						System.out.println("���� |   ���� �̸�   |  ǰ�� �̸�");
						System.out.println("-----------------------------------");
						while (rs.next()) {
							String OrderCount = rs.getString(1);
							SeedName = rs.getString(2);
							VarietyName = rs.getString(3);
							System.out.println(OrderCount + " | " + SeedName + " | " + VarietyName);
						}
					} catch (SQLException ex2) {
						System.err.println("sql error = " + ex2.getMessage());
						System.exit(1);
					}
					topOrdering(conn, stmt);
					ordering(conn, stmt);
				} else if (ageMenu == 3) {
					System.out.println("\r\n'[3] �߳� �α���� �˻��ϱ�'�� �����ϼ̽��ϴ�!\r\n");
					try {
						// ǰ�� ��ȣ ���
						String sql = "SELECT\r\n"
								+ "DENSE_RANK() OVER (ORDER BY VARIETYCOUNT DESC, Total_QUANTITY DESC) ORDER_COUNT, TEEN_SEEDNAME AS SEEDNAME, TEEN_VARIETYNAME AS VARIETYNAME\r\n"
								+ "FROM(\r\n"
								+ "     SELECT VarietyName AS TEEN_VARIETYNAME, SeedName AS TEEN_SEEDNAME, COUNT(*) AS VARIETYCOUNT, SUM(Quantity) AS Total_QUANTITY\r\n"
								+ "     FROM (\r\n"
								+ "           SELECT OD_UserID, OD_VarietyID, Age, Quantity, SeedName, VarietyName\r\n"
								+ "           FROM (\r\n"
								+ "                 SELECT OD.OD_USERID, OD.OD_VarietyID, AGE, OD.Quantity, S.SeedName, S.VarietyName\r\n"
								+ "                 FROM \"USER\" U\r\n"
								+ "                 INNER JOIN \"ORDER\" OD ON U.UserID = OD.OD_UserID\r\n"
								+ "                 FULL OUTER JOIN SEED S ON OD.OD_VarietyID = S.VarietyID)\r\n"
								+ "           WHERE 31 <= Age AND AGE < 51)\r\n"
								+ "     GROUP BY OD_VarietyID, VarietyName, SeedName\r\n"
								+ "     ORDER BY COUNT(OD_VarietyID) DESC)";
						ResultSet rs = stmt.executeQuery(sql);
						System.out.println("���� |   ���� �̸�   |  ǰ�� �̸�");
						System.out.println("-----------------------------------");
						while (rs.next()) {
							String OrderCount = rs.getString(1);
							SeedName = rs.getString(2);
							VarietyName = rs.getString(3);
							System.out.println(OrderCount + " | " + SeedName + " | " + VarietyName);
						}
					} catch (SQLException ex2) {
						System.err.println("sql error = " + ex2.getMessage());
						System.exit(1);
					}
					topOrdering(conn, stmt);
					ordering(conn, stmt);
				} else if (ageMenu == 4) {
					System.out.println("\r\n'[4] ��� �α���� �˻��ϱ�'�� �����ϼ̽��ϴ�!\r\n");
					try {
						// ǰ�� ��ȣ ���
						String sql = "SELECT\r\n"
								+ "DENSE_RANK() OVER (ORDER BY VARIETYCOUNT DESC, Total_QUANTITY DESC) ORDER_COUNT, TEEN_SEEDNAME AS SEEDNAME, TEEN_VARIETYNAME AS VARIETYNAME\r\n"
								+ "FROM(\r\n"
								+ "     SELECT VarietyName AS TEEN_VARIETYNAME, SeedName AS TEEN_SEEDNAME, COUNT(*) AS VARIETYCOUNT, SUM(Quantity) AS Total_QUANTITY\r\n"
								+ "     FROM (\r\n"
								+ "           SELECT OD_UserID, OD_VarietyID, Age, Quantity, SeedName, VarietyName\r\n"
								+ "           FROM (\r\n"
								+ "                 SELECT OD.OD_USERID, OD.OD_VarietyID, AGE, OD.Quantity, S.SeedName, S.VarietyName\r\n"
								+ "                 FROM \"USER\" U\r\n"
								+ "                 INNER JOIN \"ORDER\" OD ON U.UserID = OD.OD_UserID\r\n"
								+ "                 FULL OUTER JOIN SEED S ON OD.OD_VarietyID = S.VarietyID)\r\n"
								+ "           WHERE 51 <= Age)\r\n"
								+ "     GROUP BY OD_VarietyID, VarietyName, SeedName\r\n"
								+ "     ORDER BY COUNT(OD_VarietyID) DESC)";
						ResultSet rs = stmt.executeQuery(sql);
						System.out.println("���� |   ���� �̸�   |  ǰ�� �̸�");
						System.out.println("-----------------------------------");
						while (rs.next()) {
							String OrderCount = rs.getString(1);
							SeedName = rs.getString(2);
							VarietyName = rs.getString(3);
							System.out.println(OrderCount + " | " + SeedName + " | " + VarietyName);
						}
					} catch (SQLException ex2) {
						System.err.println("sql error = " + ex2.getMessage());
						System.exit(1);
					}
					topOrdering(conn, stmt);
					ordering(conn, stmt);
				}
			}

			else if (topMenu == 2) {
				System.out.println("\r\n'[2] �Ⱓ�� �α����'�� �����ϼ̽��ϴ�!\r\n");
				System.out.println("	[1] ����  [2] ������  [3] �Ѵ�  [4] 1��");
				int periodMenu = keyboard.nextInt();

				if (periodMenu == 1) {
					System.out.println("'[1]���� �α���� �˻��ϱ�'�� �����ϼ̽��ϴ�!\r\n");
					try {
						String sql = "SELECT\r\n"
								+ "DENSE_RANK() OVER (ORDER BY VARIETYCOUNT DESC, Total_QUANTITY DESC) ORDER_COUNT, TEEN_SEEDNAME AS SEEDNAME, TEEN_VARIETYNAME AS VARIETYNAME\r\n"
								+ "FROM(\r\n"
								+ "     SELECT VarietyName AS TEEN_VARIETYNAME, SeedName AS TEEN_SEEDNAME, COUNT(*) AS VARIETYCOUNT, SUM(Quantity) AS Total_QUANTITY\r\n"
								+ "     FROM (\r\n"
								+ "           SELECT OD_VarietyID, Purchase_Date, SeedName, VarietyName, Quantity\r\n"
								+ "           FROM (\r\n"
								+ "                 SELECT OD.OD_VarietyID, OD.Purchase_Date, S.SeedName, S.VarietyName, OD.Quantity\r\n"
								+ "                 FROM \"ORDER\" OD FULL OUTER JOIN \"SEED\" S ON OD.OD_VarietyID = S.VarietyID)\r\n"
								+ "           WHERE TO_CHAR(SYSDATE, 'YYYY-MM-DD') < Purchase_Date)\r\n"
								+ "     GROUP BY OD_VarietyID, VarietyName, SeedName\r\n"
								+ "     ORDER BY COUNT(OD_VarietyID) DESC)";
						ResultSet rs = stmt.executeQuery(sql);
						System.out.println("���� |   ���� �̸�   |  ǰ�� �̸�");
						System.out.println("-----------------------------------");
						while (rs.next()) {
							String OrderCount = rs.getString(1);
							SeedName = rs.getString(2);
							VarietyName = rs.getString(3);
							System.out.println(OrderCount + " | " + SeedName + " | " + VarietyName);
						}

					} catch (SQLException ex2) {
						System.out.println("sql error = " + ex2.getMessage());
						System.exit(1);
					}
					topOrdering(conn, stmt);
					ordering(conn, stmt);
				}

				else if (periodMenu == 2) {
					System.out.println("'[2]������ �α���� �˻��ϱ�'�� �����ϼ̽��ϴ�!\r\n");
					try {
						String sql = "SELECT\r\n"
								+ "DENSE_RANK() OVER (ORDER BY VARIETYCOUNT DESC, Total_QUANTITY DESC) ORDER_COUNT, TEEN_SEEDNAME AS SEEDNAME, TEEN_VARIETYNAME AS VARIETYNAME\r\n"
								+ "FROM(\r\n"
								+ "     SELECT VarietyName AS TEEN_VARIETYNAME, SeedName AS TEEN_SEEDNAME, COUNT(*) AS VARIETYCOUNT, SUM(Quantity) AS Total_QUANTITY\r\n"
								+ "     FROM (\r\n"
								+ "           SELECT OD_VarietyID, Purchase_Date, SeedName, VarietyName, Quantity\r\n"
								+ "           FROM (\r\n"
								+ "                 SELECT OD.OD_VarietyID, OD.Purchase_Date, S.SeedName, S.VarietyName, OD.Quantity\r\n"
								+ "                 FROM \"ORDER\" OD FULL OUTER JOIN \"SEED\" S ON OD.OD_VarietyID = S.VarietyID)\r\n"
								+ "           WHERE TO_CHAR(SYSDATE - 7, 'YYYY-MM-DD') < Purchase_Date)\r\n"
								+ "     GROUP BY OD_VarietyID, VarietyName, SeedName\r\n"
								+ "     ORDER BY COUNT(OD_VarietyID) DESC)";
						ResultSet rs = stmt.executeQuery(sql);
						System.out.println("���� |   ���� �̸�   |  ǰ�� �̸�");
						System.out.println("-----------------------------------");
						while (rs.next()) {
							String OrderCount = rs.getString(1);
							SeedName = rs.getString(2);
							VarietyName = rs.getString(3);
							System.out.println(OrderCount + " | " + SeedName + " | " + VarietyName);
						}

					} catch (SQLException ex2) {
						System.out.println("sql error = " + ex2.getMessage());
						System.exit(1);
					}
					topOrdering(conn, stmt);
					ordering(conn, stmt);
				}

				else if (periodMenu == 3) {
					System.out.println("'[3]�Ѵ� �α���� �˻��ϱ�'�� �����ϼ̽��ϴ�!\r\n");
					try {
						String sql = "SELECT\r\n"
								+ "DENSE_RANK() OVER (ORDER BY VARIETYCOUNT DESC, Total_QUANTITY DESC) ORDER_COUNT, TEEN_SEEDNAME AS SEEDNAME, TEEN_VARIETYNAME AS VARIETYNAME\r\n"
								+ "FROM(\r\n"
								+ "     SELECT VarietyName AS TEEN_VARIETYNAME, SeedName AS TEEN_SEEDNAME, COUNT(*) AS VARIETYCOUNT, SUM(Quantity) AS Total_QUANTITY\r\n"
								+ "     FROM (\r\n"
								+ "           SELECT OD_VarietyID, Purchase_Date, SeedName, VarietyName, Quantity\r\n"
								+ "           FROM (\r\n"
								+ "                 SELECT OD.OD_VarietyID, OD.Purchase_Date, S.SeedName, S.VarietyName, OD.Quantity\r\n"
								+ "                 FROM \"ORDER\" OD FULL OUTER JOIN \"SEED\" S ON OD.OD_VarietyID = S.VarietyID)\r\n"
								+ "           WHERE TO_CHAR(SYSDATE - 30, 'YYYY-MM-DD') < Purchase_Date)\r\n"
								+ "     GROUP BY OD_VarietyID, VarietyName, SeedName\r\n"
								+ "     ORDER BY COUNT(OD_VarietyID) DESC)";
						ResultSet rs = stmt.executeQuery(sql);
						System.out.println("���� |   ���� �̸�   |  ǰ�� �̸�");
						System.out.println("-----------------------------------");
						while (rs.next()) {
							String OrderCount = rs.getString(1);
							SeedName = rs.getString(2);
							VarietyName = rs.getString(3);
							System.out.println(OrderCount + " | " + SeedName + " | " + VarietyName);
						}

					} catch (SQLException ex2) {
						System.out.println("sql error = " + ex2.getMessage());
						System.exit(1);
					}
					topOrdering(conn, stmt);
					ordering(conn, stmt);
				}

				else if (periodMenu == 4) {
					System.out.println("'[4]1�� �α���� �˻��ϱ�'�� �����ϼ̽��ϴ�!\r\n");
					try {
						String sql = "SELECT\r\n"
								+ "DENSE_RANK() OVER (ORDER BY VARIETYCOUNT DESC, Total_QUANTITY DESC) ORDER_COUNT, TEEN_SEEDNAME AS SEEDNAME, TEEN_VARIETYNAME AS VARIETYNAME\r\n"
								+ "FROM(\r\n"
								+ "     SELECT VarietyName AS TEEN_VARIETYNAME, SeedName AS TEEN_SEEDNAME, COUNT(*) AS VARIETYCOUNT, SUM(Quantity) AS Total_QUANTITY\r\n"
								+ "     FROM (\r\n"
								+ "           SELECT OD_VarietyID, Purchase_Date, SeedName, VarietyName, Quantity\r\n"
								+ "           FROM (\r\n"
								+ "                 SELECT OD.OD_VarietyID, OD.Purchase_Date, S.SeedName, S.VarietyName, OD.Quantity\r\n"
								+ "                 FROM \"ORDER\" OD FULL OUTER JOIN \"SEED\" S ON OD.OD_VarietyID = S.VarietyID)\r\n"
								+ "           WHERE TO_CHAR(SYSDATE - 365, 'YYYY-MM-DD') < Purchase_Date)\r\n"
								+ "     GROUP BY OD_VarietyID, VarietyName, SeedName\r\n"
								+ "     ORDER BY COUNT(OD_VarietyID) DESC)";
						ResultSet rs = stmt.executeQuery(sql);
						System.out.println("���� |   ���� �̸�   |  ǰ�� �̸�");
						System.out.println("-----------------------------------");
						while (rs.next()) {
							String OrderCount = rs.getString(1);
							SeedName = rs.getString(2);
							VarietyName = rs.getString(3);
							System.out.println(OrderCount + " | " + SeedName + " | " + VarietyName);
						}

					} catch (SQLException ex2) {
						System.out.println("sql error = " + ex2.getMessage());
						System.exit(1);
					}
					topOrdering(conn, stmt);
					ordering(conn, stmt);
				}
			}
		}
	}

	public static void home(Connection conn, Statement stmt) {
		System.out.println("welcome");
	}

	public static void topOrdering(Connection conn, Statement stmt) {
		String inputSeedName = "";

		// ���� ǰ�� ����̶� ������� ���
		System.out.println("\r\n<< ����/�뿩 �Ͻ� ������ �̸��� �˷��ּ��� >>");
		inputSeedName = keyboard.next();

		try {
			String sql = "SELECT S.VarietyName, ORG.OrgName, ORG.Org_Purpose \r\n"
					+ "FROM \"SEED\" S INNER JOIN HAS H ON S.VarietyID = H.VarietyID INNER JOIN ORGANIZATION ORG ON H.OrgName = ORG.OrgName \r\n"
					+ "WHERE S.SeedName LIKE '" + inputSeedName + "'";
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("<< �˻��Ͻ� ������ ǰ���� �� ������ �����ϰ� �ִ� ����� ����Դϴ� >>");
			System.out.println("Selected SEEDNAME: " + inputSeedName);
			System.out.println("VARIETYNAME | ORGANIZATION NAME               | PURPOSE");
			System.out.println("--------------------------------------------------------");

			while (rs.next()) {
				String outputVarietyName = rs.getString(1);
				String outputOrgName = rs.getString(2);
				String outputOrgPurpose = rs.getString(3);
				if (outputOrgPurpose.equalsIgnoreCase("L"))
					outputOrgPurpose = "�뿩";
				else if (outputOrgPurpose.equalsIgnoreCase("S"))
					outputOrgPurpose = "�Ǹ�";
				System.out.println(outputVarietyName + " | " + outputOrgName + " | " + outputOrgPurpose);
			}
		} catch (SQLException ex2) {
			System.err.println("sql error = " + ex2.getMessage());
			System.exit(1);
		}
	}

	public static void ordering(Connection conn, Statement stmt) {
		System.out.println("\r\n���� �� �뿩�� ���ϴ� ������ �̸�, ǰ��, ����� �������ּ���.");
		System.out.println("(�������θ� �����Ͽ� ���� �Է����ּ���!)");
		String inputSeedName = keyboard.next();
		String inputVarietyName = keyboard.next();
		String inputOrgName = keyboard.next();

		String inputOrgPurpose = "";
		String PurposeKOR = "";
		int inputQuantity = 0;
		// ���� �ڵ� �Է�
		try {
			String sql = "SELECT Org_Purpose \r\n" + "FROM ORGANIZATION \r\n" + "WHERE OrgName LIKE '" + inputOrgName
					+ "' \r\n";

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				inputOrgPurpose = rs.getString(1);
			}
			if (inputOrgPurpose.equalsIgnoreCase("L"))
				PurposeKOR = "�뿩";
			else if (inputOrgPurpose.equalsIgnoreCase("S"))
				PurposeKOR = "����";
			else if (inputOrgPurpose.equalsIgnoreCase("A")) {
				System.out.println("�� ����� ���ſ� �뿩 ��ΰ� ������ ����Դϴ�. ���Ÿ� ���Ͻø� '����'��, �뿩�� ���Ͻø� '�뿩'�� �Է����ּ���.");
				PurposeKOR = keyboard.next();
			}
		} catch (SQLException ex2) {
			System.err.println("sql error = " + ex2.getMessage());
			System.exit(1);
		}
		if (PurposeKOR.equalsIgnoreCase("�뿩")) {
			System.out.println("�뿩�� �� ���� 5g�� �����ϹǷ� �뿩�Ͻô� ���� �ڵ� �ԷµǾ����ϴ�.");
			inputQuantity = 5;
		} else {
			System.out.println("\r\n<< �󸶳� ���� ���� ������ ����/�뿩 ���� �Է����ּ���. (������ g�Դϴ�!) >>");
			inputQuantity = keyboard.nextInt();
			keyboard.nextLine();
		}

		// �ֹ� ���� Ȯ��
		System.out.println("\r\n<< �ֹ� ������ Ȯ�����ֽð�, ������ YES, Ʋ���� NO�� �Է��� �ּ��� >>");
		System.out.println("���� �̸�: " + inputSeedName + ", ǰ����: " + inputVarietyName + ", �뿩/���� ���: " + inputOrgName
				+ " (" + PurposeKOR + "), ����: " + inputQuantity + "g");

		String answer = keyboard.next();
		if (answer.equalsIgnoreCase("YES")) {
			/*---------------------------------------------*/
			// �α��� Ŭ���� ��������� INSERT�κ� �����ϰڽ��ϴ�
			/*---------------------------------------------*/
			System.out.printf("\r\n���� ���̵�: %s\n", now_USER_ID);
			String outputVarietyID = "id";
			try {
				// VarietyID ���ϱ�
				String sql = "SELECT S.VarietyID \r\n"
						+ "FROM \"SEED\" S FULL OUTER JOIN HAS H ON S.VarietyID = H.VarietyID \r\n"
						+ "WHERE S.SeedName LIKE '" + inputSeedName + "' \r\n" + "AND S.VarietyName LIKE'"
						+ inputVarietyName + "' \r\n" + "AND H.OrgName LIKE '" + inputOrgName + "'";
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					outputVarietyID = rs.getString(1);
				}
			} catch (SQLException ex2) {
				System.err.println("sql error = " + ex2.getMessage());
				System.exit(1);
			}
			if (PurposeKOR.equalsIgnoreCase("����"))
				inputOrgPurpose = "B";
			else if (PurposeKOR.equalsIgnoreCase("�뿩"))
				inputOrgPurpose = "R";
			try {
				String sql = "INSERT INTO \"ORDER\" VALUES (ORDER_SEQ.NEXTVAL, '" + now_USER_ID
						+ "', TO_CHAR(SYSDATE, 'YYYY-MM-qDD HH24:MI'), '" + inputOrgPurpose + "', '" + inputOrgName
						+ "', '" + outputVarietyID + "', " + inputQuantity + ")";
				System.out.println(sql);
				ResultSet rs = stmt.executeQuery(sql);
				System.out.println("\r\n<< �ֹ��� �Ϸ�Ǿ����ϴ�. >>");
				conn.commit();
			} catch (SQLException ex2) {
				System.err.println("sql error = " + ex2.getMessage());
				System.exit(1);
			}
		} else if (answer.equalsIgnoreCase("NO")) {
			System.out.println("�޴� ���� �������� ���ư��ϴ�.");
		}
	}

	// ������ �ֹ� ��� ù ������_����� �˻�/��� �˻�
	public static void manOrderList(Connection conn, Statement stmt) {
		while (true) {
			System.out.println("[1] ����� �ֹ� ��� �˻�  [2] ��� �ֹ� ��� �˻�");
			int opt = keyboard.nextInt();
			if (opt == 1) {
				myOrderList(conn, stmt);
				break;
			} else if (opt == 2) {
				OrgOrderList(conn, stmt);
				break;
			} else
				System.out.println("�߸��� �ɼ��� �����ϼ̽��ϴ�. �ٽ� �������ּ���.");
		}
	}

	// ����ڿ� �����ڰ� ����� �ֹ� ��� ��� �Լ�_���̵�� �ֹ� ��� �˻�
	public static void myOrderList(Connection conn, Statement stmt) {
		System.out.println("<< �ֹ� ��� ����� ���� ���̵� �Է����ּ��� >>");
		System.out.print("ID: ");
		String UserID = keyboard.next();
		try {
			// VarietyID ���ϱ�
			String sql = "SELECT OrderNum, Purchase_Date, SeedName, VarietyName, Quantity, OD_OrgName, Order_Purpose\r\n"
					+ "FROM \"ORDER\" INNER JOIN \"SEED\" ON OD_VarietyID = VarietyID\r\n" + "WHERE OD_UserID LIKE '"
					+ UserID + "'";
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println(UserID + "�� �ֹ� ����");
			System.out.println(
					"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			while (rs.next()) {
				String outputOrderNum = rs.getString(1);
				String outputPurchaseDate = rs.getString(2);
				String outputSeedName = rs.getString(3);
				String outputVarietyName = rs.getString(4);
				String outputQuantity = rs.getString(5);
				String outputOrgName = rs.getString(6);
				String outputOrderPurpose = rs.getString(7);
				if (outputOrderPurpose.equalsIgnoreCase("R"))
					outputOrderPurpose = "�뿩";
				else if (outputOrderPurpose.equalsIgnoreCase("B"))
					outputOrderPurpose = "����";
				System.out.println("�ֹ���ȣ: " + outputOrderNum + " | �ֹ���¥: " + outputPurchaseDate + " | �ֹ��� ���� �̸�: "
						+ outputSeedName + " | �ֹ��� ǰ�� �̸� : " + outputVarietyName + " | �ֹ���(g) : " + outputQuantity
						+ " | �ֹ� ���: " + outputOrgName + " | �ֹ� ����: " + outputOrderPurpose);
			}

		} catch (SQLException ex2) {
			System.err.println("sql error = " + ex2.getMessage());
			System.exit(1);
		}
	}

	// ����� �����ڰ� ����� �ֹ� ��� ��� �Լ�_��� �̸����� �ֹ� ��� �˻�
	public static void OrgOrderList(Connection conn, Statement stmt) {
		System.out.println("<< �ֹ� ��� ����� ���� ������� �Է����ּ��� >>");
		System.out.print("OrgName: ");
		String OrgName = keyboard.next();
		try {
			// VarietyID ���ϱ�
			String sql = "SELECT OrderNum, OD_UserID, Purchase_Date, SeedName, VarietyName, Quantity\r\n"
					+ "FROM \"ORDER\" INNER JOIN \"SEED\" ON OD_VarietyID = VarietyID\r\n" + "WHERE OD_OrgName LIKE '"
					+ OrgName + "'";
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("���" + OrgName + "�� �ֹ��� ����");
			System.out.println(
					"-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			while (rs.next()) {
				String outputOrderNum = rs.getString(1);
				String outputUserID = rs.getString(2);
				String outputPurchaseDate = rs.getString(3);
				String outputSeedName = rs.getString(4);
				String outputVarietyName = rs.getString(5);
				String outputQuantity = rs.getString(6);
				System.out.println("�ֹ���ȣ: " + outputOrderNum + "�ֹ��� ���� ���̵�: " + outputUserID + " | �ֹ���¥: "
						+ outputPurchaseDate + " | �ֹ��� ���� �̸�: " + outputSeedName + " | �ֹ��� ǰ�� �̸� : " + outputVarietyName
						+ " | �ֹ���(g) : " + outputQuantity);
				System.out.println(
						"-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			}
		} catch (SQLException ex2) {
			System.err.println("sql error = " + ex2.getMessage());
			System.exit(1);
		}
	}

	public static void BBwrite(Connection conn, Statement stmt) {

		String Purpose;
		String Title;
		String UserID;
		String Content;

		Scanner scan = new Scanner(System.in);

		System.out.println("���� : ");
		Purpose = scan.nextLine();
		System.out.println("���� : ");
		Title = scan.nextLine();
		System.out.println("���̵� : ");
		UserID = scan.nextLine();
		System.out.println("���� : ");
		Content = scan.nextLine();

		try {
			String sql = "insert into BULLETIN_BOARD" + "(BWNum,BWPurpose,BWTitle,BUserID,BWTime,BWContent)" + "values"
					+ "(BW_SEQ.NEXTVAL, '" + Purpose + "', '" + Title + "', '" + UserID
					+ "', TO_CHAR(SYSDATE, 'YYYY.MM.DD HH24:MI'), '" + Content + "')";

			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(2, Purpose);
			pstmt.setString(3, Title);
			pstmt.setString(4, UserID);
			pstmt.setString(6, Content);
			int rowCount = pstmt.executeUpdate();
			System.out.println(rowCount + "���� ���� �߰��Ǿ����ϴ�.");
		} catch (SQLException ex2) {
			System.err.println("sql error = " + ex2.getMessage());
			scan.close();
			System.exit(1);
		}
		scan.close();
	}

	public static void NBwrite(Connection conn, Statement stmt) {

		String Title;
		String Content;

		Scanner scan = new Scanner(System.in);

		System.out.println("���� : ");
		Title = scan.nextLine();
		System.out.println("���� : ");
		Content = scan.nextLine();

		try {
			// VarietyID ���ϱ�
			String sql = "insert into NOTICE_BOARD" + "(NWNum,NWTitle,NWTime,NWContent)" + "values"
					+ "(NW_SEQ.NEXTVAL, '" + Title + "', TO_CHAR(SYSDATE, 'YYYY.MM.DD HH24:MI'), '" + Content + "')";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(2, Title);
			pstmt.setString(4, Content);
			int rowCount = pstmt.executeUpdate();
			System.out.println(rowCount + "���� ���� �߰��Ǿ����ϴ�.");

			pstmt.close();
			conn.close();
		} catch (SQLException ex2) {
			System.err.println("sql error = " + ex2.getMessage());
			scan.close();
			System.exit(1);
		}
		scan.close();
	}

	public static void BBread(Connection conn, Statement stmt) {
		System.out.println("1. �������� �˻�  2.ȸ�� ���̵�� �˻�");
		Scanner a = new Scanner(System.in);
		int se = keyboard.nextInt();
		if (se == 1) {
			System.out.println("�˻��� ������ �Է��ϼ���\n");
			String title = keyboard.nextLine();
			keyboard.nextLine();

			try {
				String sql = "select *" + "from BULLETIN_BOARD" + "where BWTitle LIKE '%" + title + "%'";

				// Class.forName("oracle.jdbc.OracleDriver");
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					int no = rs.getInt("BWNum");
					String p = rs.getString("BWPurpose");
					String title1 = rs.getString("BWTitle");
					String id = rs.getString("BUserID");
					String co = rs.getString("BWContent");

					System.out.println(no + ", " + p + ", " + title1 + ", " + id + ", " + co);
				}
			} catch (SQLException ex2) {
				System.err.println("sql error = " + ex2.getMessage());
				a.close();
				System.exit(1);
			}
		} else {
			System.out.println("�˻��� ȸ�� ���̵� �Է��ϼ���\n");
			String id = keyboard.nextLine();

			try {
				String sql = "select *" + "from BULLETIN_BOARD" + "where BUserID LIKE '" + id + "' ";

				// Class.forName("oracle.jdbc.OracleDriver");

				PreparedStatement pstmt = conn.prepareStatement(sql);

				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					int no = rs.getInt("BWNum");
					String p = rs.getString("BWPurpose");
					String title = rs.getString("BWTitle");
					String id1 = rs.getString("BUserID");
					String co = rs.getString("BWContent");
					System.out.println(no + ", " + p + ", " + title + ", " + id1 + ", " + co);
				}
			} catch (SQLException ex2) {
				System.err.println("sql error = " + ex2.getMessage());
				a.close();
				System.exit(1);
			}
		}
		a.close();
	}

	public static void NBread(Connection conn, Statement stmt) {
		try {
			System.out.println("�˻��� ������ �Է��ϼ���\n");
			String title = keyboard.nextLine();

			String sql = "select *" + "from NOTICE_BOARD" + "where NWTitle LIKE '%" + title + "%' ";

			// Class.forName("oracle.jdbc.OracleDriver");

			PreparedStatement pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int no = rs.getInt("NWNum");
				String title1 = rs.getString("NWTitle");
				String con = rs.getString("NWContent");

				System.out.println(no + ", " + title1 + ", " + con);
			}
		} catch (SQLException ex2) {
			System.err.println("sql error = " + ex2.getMessage());
			System.exit(1);
		}
	}

	public static void BBdelete(Connection conn, Statement stmt) {
		int num;

		Scanner scan = new Scanner(System.in);

		System.out.println("������ ���ϴ� �۹�ȣ : ");
		num = scan.nextInt();

		try {
			String sql = "delete from BULLETIN_BOARD where BWNum = ?";
			// Class.forName("oracle.jdbc.OracleDriver");

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			int rowCount = pstmt.executeUpdate();
			System.out.println(rowCount + "���� ���� �����Ǿ����ϴ�.");
		} catch (SQLException ex2) {
			System.err.println("sql error = " + ex2.getMessage());
			scan.close();
			System.exit(1);
		}
		
		scan.close();
	}

	public static void NBdelete(Connection conn, Statement stmt) {
		int num;

		Scanner scan = new Scanner(System.in);

		System.out.println("������ ���ϴ� �۹�ȣ : ");
		num = scan.nextInt();

		try {
			String sql = "delete from NOTICE_BOARD where NWNum = ?";
			// Class.forName("oracle.jdbc.OracleDriver");

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			int rowCount = pstmt.executeUpdate();
			System.out.println(rowCount + "���� ���� �����Ǿ����ϴ�.");
		} catch (SQLException ex2) {
			System.err.println("sql error = " + ex2.getMessage());
			scan.close();
			System.exit(1);
		}
		scan.close();
	}

	/* ���������� �Լ� */
	public static void Mypage(Connection conn, Statement stmt) {

		int menu_num, res;
		int wrong_count = 0;
		String check_ID, check_PW, sql, 
				change_Name, change_PW, change_Address, 
				change_Gender, change_Email;
		int change_Age, change_PhoneN;
		
		System.out.println("-----------------------------------");
		System.out.println("---------------�޴�-----------------");
		System.out.println("[1] ȸ�� ���� ����  [2] ȸ�� Ż��");
		System.out.println("-----------------------------------");
		System.out.println("-----------------------------------");
		System.out.println("�޴��� �����ϼ���: ");

		menu_num = keyboard.nextInt();

		switch (menu_num) {
		/*[1] ȸ�� ���� ����*/
		case 1:
			System.out.println("[1] ȸ�� ���� ������ �����ϼ̽��ϴ�!\n");
			System.out.println("-----------------------------------");
			System.out.println("ȸ�� ���� ������ ���� ���� ������ �����մϴ�.");
			System.out.println("���̵�� ��й�ȣ�� �ٽ� �� �� �Է����ּ���.");
			
			while(true)
			{
				if (wrong_count > 1) {
					System.out.println("���������� �����Ͽ����ϴ�!\n\n\n");
					wrong_count = 0;
					return;
				}
				
				System.out.println("-----------------------------------");
				System.out.println("���̵�� ��й�ȣ�� �Է����ּ���.\n\n");
				System.out.printf("<���̵�>: ");
				check_ID = keyboard.next();
				System.out.printf("<��й�ȣ>: ");
				check_PW = keyboard.next();

				if (now_USER_ID.equals(check_ID) && now_USER_PW.equals(check_PW)) 
				{
					System.out.printf("%s �� ���� ������ �Ϸ��Ͽ����ϴ�!\n", check_ID);
					break;
				} 
				else 
				{
					System.out.println("�������� �ʴ� ���̵��̰ų� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.\n");
					wrong_count++;
				}
			}
			
			System.out.println("������ ������ �������ּ���. ��, ���̵�� ������ �Ұ����մϴ�.");
			System.out.println("-----------------------------------");
			System.out.println("---------------�޴�-----------------");
			System.out.println("[1] �̸�     [2] ��й�ȣ  [3] �ּ�  [4] ����");
			System.out.println("[5] ��ȭ��ȣ  [6] ����     [7] �̸��� �ּ�");
			System.out.println("-----------------------------------");
			System.out.println("-----------------------------------");
			System.out.println("�޴��� �����ϼ���: ");
			menu_num = keyboard.nextInt();
			
			switch(menu_num)
			{
			case 1:
				System.out.println("[1] �̸��� �����մϴ�.");
				System.out.println("-----------------------------------");
				System.out.printf("���� ��ϵ� �̸�: %s\n", now_USER_ID);
				System.out.println("�����Ͻ� �̸��� �Է����ּ���.");
				System.out.printf("<����>: ");
				change_Name = keyboard.next();
				
				try {
					sql = "UPDATE \"USER\" SET USER_NAME = "
							+ "\'"+change_Name+"\' WHERE USERID = \'" + now_USER_ID + "\'";
					System.out.println(sql);
					res = stmt.executeUpdate(sql);
					conn.commit();
				}catch(SQLException ex2) {
					System.out.println("sql error = " + ex2.getMessage());
					System.exit(1);
				}
				break;
			case 2:
				System.out.println("[2] ��й�ȣ�� �����մϴ�.");
				System.out.println("-----------------------------------");
				System.out.println("�����Ͻ� ��й�ȣ�� �Է����ּ���: ");
				System.out.printf("<��й�ȣ>: ");
				change_PW = keyboard.next();
				
				try {
					sql = "UPDATE \"USER\" SET PW = "
							+ "\'"+change_PW+"\' WHERE USERID = \'" + now_USER_ID + "\'";
					
					res = stmt.executeUpdate(sql);
					conn.commit();
				}catch(SQLException ex2) {
					System.out.println("sql error = " + ex2.getMessage());
					System.exit(1);
				}
				break;
			case 3:
				System.out.println("[3] �ּҸ� �����մϴ�.");
				System.out.println("-----------------------------------");
				System.out.println("�����Ͻ� �ּҸ� �Է����ּ���: ");
				System.out.printf("<�ּ�>: ");
				change_Address = keyboard.next();
				
				try {
					sql = "UPDATE \"USER\" SET ADDRESS = "
							+ "\'"+change_Address+"\' WHERE USERID = \'" + now_USER_ID + "\'";
					
					res = stmt.executeUpdate(sql);
					conn.commit();
				}catch(SQLException ex2) {
					System.out.println("sql error = " + ex2.getMessage());
					System.exit(1);
				}
				break;
			case 4:
				System.out.println("[4] ���̸� �����մϴ�.");
				System.out.println("-----------------------------------");
				System.out.println("�����Ͻ� ���̸� �Է����ּ���: ");
				System.out.printf("<����>: ");
				change_Age = keyboard.nextInt();
				
				try {
					sql = "UPDATE \"USER\" SET AGE = "
							+change_Age+" WHERE USERID = \'" + now_USER_ID + "\'";
					
					res = stmt.executeUpdate(sql);
					conn.commit();
				}catch(SQLException ex2) {
					System.out.println("sql error = " + ex2.getMessage());
					System.exit(1);
				}
				break;
			case 5:
				System.out.println("[5] ��ȭ��ȣ�� �����մϴ�.");
				System.out.println("-----------------------------------");
				System.out.println("�����Ͻ� ��ȭ��ȣ�� �Է����ּ���: ");
				System.out.printf("<��ȭ��ȣ>: 010-xxxx-xxxx �������� �Է��� �ּ���: ");
				change_PhoneN = keyboard.nextInt();
				
				try {
					sql = "UPDATE \"USER\" SET PHONE_NUM = "
							+change_PhoneN+" WHERE USERID = \'" + now_USER_ID + "\'";
					
					res = stmt.executeUpdate(sql);
					conn.commit();
				}catch(SQLException ex2) {
					System.out.println("sql error = " + ex2.getMessage());
					System.exit(1);
				}
				break;
			case 6:
				System.out.println("[6] ������ �����մϴ�.");
				System.out.println("-----------------------------------");
				System.out.println("�����Ͻ� ������ �Է����ּ���: ");
				System.out.printf("<����>: F(����) �Ǵ� M(����)�� �Է��� �ּ���: ");
				change_Gender = keyboard.next();
				
				try {
					sql = "UPDATE \"USER\" SET GENDER = "
							+ "\'"+change_Gender+"\'"+"\' WHERE USERID = \'" + now_USER_ID + "\'";
					
					res = stmt.executeUpdate(sql);
					conn.commit();
				}catch(SQLException ex2) {
					System.out.println("sql error = " + ex2.getMessage());
					System.exit(1);
				}
				break;
			case 7:
				System.out.println("[7] �̸��� �ּҸ� �����մϴ�.");
				System.out.println("-----------------------------------");
				System.out.println("�����Ͻ� �̸��� �ּҸ� �Է����ּ���: ");
				System.out.printf("<�̸��� �ּ�>: ");
				change_Email = keyboard.next();
				
				try {
					sql = "UPDATE \"USER\" SET EMAIL_ADR = "
							+ "\'"+change_Email+"\' WHERE USERID = \'" + now_USER_ID + "\'";
					
					res = stmt.executeUpdate(sql);
					conn.commit();
				}catch(SQLException ex2) {
					System.out.println("sql error = " + ex2.getMessage());
					System.exit(1);
				}
				break;
			}
			break;
		/*[2] ȸ�� Ż��*/
		case 2:
			String yesOrNo;
			System.out.println("[2] ȸ�� Ż�� �����ϼ̽��ϴ�!\n");
			System.out.println("-----------------------------------");
			System.out.println("ȸ�� Ż�� ���� ���� ������ �����մϴ�.");
			System.out.println("���̵�� ��й�ȣ�� �ٽ� �� �� �Է����ּ���.");
			
			while(true)
			{
				if (wrong_count > 1) {
					System.out.println("���������� �����Ͽ����ϴ�!\n\n\n");
					return;
				}
				
				System.out.println("-----------------------------------");
				System.out.println("���̵�� ��й�ȣ�� �Է����ּ���.\n\n");
				System.out.printf("<���̵�>: ");
				check_ID = keyboard.next();
				System.out.printf("<��й�ȣ>: ");
				check_PW = keyboard.next();

				if (now_USER_ID.equals(check_ID) && now_USER_PW.equals(check_PW)) 
				{
					System.out.printf("%s �� ���� ������ �Ϸ��Ͽ����ϴ�!\n", check_ID);
					break;
				} 
				else 
				{
					System.out.println("�������� �ʴ� ���̵��̰ų� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.\n");
					wrong_count++;
				}
			}
			
			System.out.println("-----------------------------------");
			System.out.println("ȸ�� Ż�� �����մϴ�.");
			System.out.println("ȸ�� Ż�� ������ �����Ͻðڽ��ϱ�? (y �Ǵ� n�� �Է����ּ���.)");
			
			yesOrNo = keyboard.next();
			
			if(yesOrNo.equalsIgnoreCase("y"))
			{
				System.out.println("ȸ��Ż�� ��ȷ�˴ϴ�.");
				
				/*Ż�� ���� ��, �ֹ� ��� ��� ����*/
				/*Ż�� ���� ��, �ۼ��� Ŀ�´�Ƽ �� ��� ��� ����*/
				/*Ż�� ����*/
				
				System.out.println("ȸ��Ż�� �Ϸ��Ͽ����ϴ�. �̿����ּż� �����մϴ�.");
			}
			else if(yesOrNo.equalsIgnoreCase("n"))
			{
				System.out.println("ȸ��Ż�� �������� �ʽ��ϴ�.");
				System.out.println("������������ ���ư��ϴ�.");
			}
			break;
		} /*���������� �޴� ���� switch�� ����*/

	}
}