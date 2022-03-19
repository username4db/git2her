/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.git2her;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@Controller
@SpringBootApplication
public class App {

	private final static Logger LOGGER = LogManager.getLogger(App.class);

//  @Value("${spring.datasource.url}")
//  private String dbUrl;
//
//  @Autowired
//  private DataSource dataSource;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(App.class, args);
	}

//  @RequestMapping("/")
//  String index() {
//    return "index";
//  }
//
//  @RequestMapping("/db")
//  String db(Map<String, Object> model) {
//    try (Connection connection = dataSource.getConnection()) {
//      Statement stmt = connection.createStatement();
//      stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
//      stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
//      ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");
//
//      ArrayList<String> output = new ArrayList<String>();
//      while (rs.next()) {
//        output.add("Read from DB: " + rs.getTimestamp("tick"));
//      }
//
//      model.put("records", output);
//      return "db";
//    } catch (Exception e) {
//      model.put("message", e.getMessage());
//      return "error";
//    }
//  }
//
//  @Bean
//  public DataSource dataSource() throws SQLException {
//    if (dbUrl == null || dbUrl.isEmpty()) {
//      return new HikariDataSource();
//    } else {
//      HikariConfig config = new HikariConfig();
//      config.setJdbcUrl(dbUrl);
//      return new HikariDataSource(config);
//    }
//  }

}
