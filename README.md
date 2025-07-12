<h1 align="center">🏡 Tunisia Guest House Finder – Android Application</h1>

<p align="center">
  <em>An Android app that helps visitors discover and manage guest houses across Tunisia with an intuitive interface and simple admin features.</em>
</p>

<hr/>

<h2>🎯 Objective</h2>

<p>
  This project demonstrates the development of a mobile application that allows users to search, filter, and explore guest houses in Tunisia.
  It also provides administrators with the ability to add, update, and delete guest house records through secure access.
</p>

<h2>⚙️ Project Architecture</h2>

The project consists of two main user flows:

<ol>
  <li><strong>Visitor Flow</strong>
    <ul>
      <li>
        The main screen (<code>activity_main.xml</code>) displays:
        <ul>
          <li>An introduction text explaining the purpose of the app.</li>
          <li>Two main buttons:
            <ul>
              <li><strong>"All Visitors"</strong>: Navigates to the visitor page.</li>
              <li><strong>"Just For Admin"</strong>: Opens the admin login dialog.</li>
            </ul>
          </li>
        </ul>
      </li>
      <li>
        Upon clicking <strong>"All Visitors"</strong>:
        <ul>
          <li>A toast message <code>"Navigating to Visitor Page"</code> is shown.</li>
          <li>The user is redirected to <code>visitor_page.xml</code>.</li>
          <li>The page displays a scrollable list of all guest houses stored in the database.</li>
          <li>Each row shows:
            <ul>
              <li>Image of the guest house</li>
              <li>Name</li>
              <li>Region and City</li>
              <li>Nightly price</li>
            </ul>
          </li>
          <li>Visitors can filter guest houses using spinners to select:
            <ul>
              <li>Region (North, South, East, West)</li>
              <li>City</li>
              <li>Price range</li>
            </ul>
          </li>
          <li>A button allows returning to the main page, displaying the toast <code>"Navigating to Main Page"</code>.</li>
        </ul>
      </li>
    </ul>
  </li>

  <li><strong>Admin Flow</strong>
    <ul>
      <li>
        When clicking <strong>"Just For Admin"</strong>:
        <ul>
          <li>A dialog appears prompting for:
            <ul>
              <li><code>Admin ID</code></li>
              <li><code>Password</code></li>
            </ul>
          </li>
          <li>Credentials:
            <ul>
              <li>ID: <code>admin</code></li>
              <li>Password: <code>password123</code></li>
            </ul>
          </li>
          <li>If authentication succeeds:
            <ul>
              <li>The user is redirected to the admin dashboard (<code>AdminPageActivity</code>).</li>
              <li>A list of all guest houses is displayed in a scrollable view.</li>
            </ul>
          </li>
        </ul>
      </li>

      <li>
        Admin can:
        <ul>
          <li><strong>Update/Delete a Guest House</strong>:
            <ul>
              <li>Clicking a guest house row opens a dialog (<code>AdminUpdateDeletePage</code>).</li>
              <li>The details are populated into editable fields:
                <ul>
                  <li>ID, Name, Region, City, Price, Image</li>
                </ul>
              </li>
              <li>Admin can:
                <ul>
                  <li><strong>Delete</strong> the record, showing:
                    <ul>
                      <li>Success: <code>"Guest successfully deleted."</code></li>
                      <li>Failure: <code>"Failed to delete guest."</code></li>
                    </ul>
                  </li>
                  <li><strong>Update</strong> one or more fields after validation (no empty fields), showing:
                    <ul>
                      <li><code>"Guest successfully updated."</code></li>
                    </ul>
                  </li>
                </ul>
              </li>
            </ul>
          </li>

          <li><strong>Add a New Guest House</strong>:
            <ul>
              <li>Clicking the add button navigates to <code>admin_activity.xml</code> (<code>AddGuestActivity</code>).</li>
              <li>The admin fills in all details of the new guest house.</li>
              <li>Upon submission:
                <ul>
                  <li>Success: <code>"Guest added successfully!"</code></li>
                  <li>Failure: <code>"Error while adding guest."</code></li>
                </ul>
              </li>
            </ul>
          </li>

          <li>Return to the home screen at any time.</li>
        </ul>
      </li>
    </ul>
  </li>
</ol>

<h2>🧰 Technologies Used</h2>

<ul>
  <li><strong>Android Development</strong>:
    <ul>
      <li>Java (Android SDK)</li>
      <li>XML for layouts and UI design</li>
      <li>Intents and Toasts for navigation and feedback</li>
    </ul>
  </li>
  <li><strong>Database</strong>:
    <ul>
      <li>SQLite (or Room) for storing guest house data</li>
    </ul>
  </li>
  <li><strong>Development Environment</strong>:
    <ul>
      <li>Android Studio</li>
    </ul>
  </li>
</ul>

<h2>✨ Key Features</h2>

<ul>
  <li><strong>Visitor Search</strong>:
    <ul>
      <li>Filter guest houses by region, city, and price.</li>
      <li>View detailed information in a clear list format.</li>
    </ul>
  </li>
  <li><strong>Admin Management</strong>:
    <ul>
      <li>Secure login for administrators.</li>
      <li>Update and delete existing guest house records.</li>
      <li>Add new guest houses to the database.</li>
    </ul>
  </li>
  <li><strong>Simple Navigation</strong>:
    <ul>
      <li>Intuitive buttons and clear toast messages guide the user.</li>
    </ul>
  </li>
</ul>

<h2>🌍 Real-World Impact</h2>

<p>
  This application showcases how mobile technologies can simplify travel planning and accommodation management. It can be adapted for:
</p>

<ul>
  <li><strong>Travel Agencies</strong>:
    <ul>
      <li>Helping tourists discover and compare guest houses across Tunisia.</li>
    </ul>
  </li>
  <li><strong>Hospitality Businesses</strong>:
    <ul>
      <li>Enabling owners to manage their listings conveniently.</li>
    </ul>
  </li>
  <li><strong>Educational Use</strong>:
    <ul>
      <li>Serving as an example project for students learning Android development and CRUD operations.</li>
    </ul>
  </li>
</ul>

<h2>✅ Conclusion</h2>

<p>
  The <strong>Tunisia Guest House Finder</strong> is a complete Android application that demonstrates how to combine user-friendly interfaces with simple administrative tools to create a practical and valuable solution.
  Whether for travelers, administrators, or learners, this project exemplifies how technology can enhance the guest house discovery experience.
</p>

<hr/>

<p align="center">
  <img src="https://media.giphy.com/media/hvRJCLFzcasrR4ia7z/giphy.gif" width="300" alt="Done"/>
</p>
