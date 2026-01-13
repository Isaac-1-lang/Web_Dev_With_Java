# Sessions and Cookies Guide

## 📚 Overview

This guide explains the difference between **Sessions** and **Cookies** in Java web applications, with practical examples from your codebase.

---

## 🔑 Key Differences

| Feature | **SESSIONS** | **COOKIES** |
|---------|-------------|------------|
| **Storage Location** | Server-side (in memory/disk) | Client-side (browser) |
| **Security** | More secure (data on server) | Less secure (can be read/modified) |
| **Size Limit** | Large (can store objects) | Small (4KB max per cookie) |
| **Persistence** | Expires when browser closes (or timeout) | Can persist for days/months |
| **Data Type** | Can store any Java Object | Only strings |
| **Access** | Only server can access | Client (browser/JavaScript) can access |

---

## 🎯 When to Use What?

### Use **SESSIONS** for:
- ✅ User authentication (login status)
- ✅ Sensitive data (user ID, permissions)
- ✅ Shopping cart items
- ✅ Temporary data that shouldn't persist
- ✅ Large objects

### Use **COOKIES** for:
- ✅ "Remember Me" functionality
- ✅ User preferences (theme, language)
- ✅ Tracking/analytics
- ✅ Non-sensitive data
- ✅ Data that should persist after browser closes

---

## 📖 SESSIONS - Detailed Explanation

### What is a Session?

A **session** is a way to store user-specific data on the **server** between multiple HTTP requests. Each user gets a unique session ID that's sent to the browser as a cookie (JSESSIONID), but the actual data stays on the server.

### How Sessions Work:

```
1. User makes first request → Server creates session → Sends JSESSIONID cookie to browser
2. Browser stores JSESSIONID cookie
3. Next request → Browser sends JSESSIONID → Server finds session using ID
4. Server can read/write data in that session
5. Session expires after inactivity (default: 30 minutes)
```

### Session Code Examples:

#### **Creating/Getting a Session:**

```java
// Get existing session OR create new one if doesn't exist
HttpSession session = request.getSession();

// Get existing session ONLY (returns null if doesn't exist)
HttpSession session = request.getSession(false);
```

#### **Storing Data in Session:**

```java
// Store any object
session.setAttribute("user", username);
session.setAttribute("userObject", userObject);
session.setAttribute("loginTime", System.currentTimeMillis());
```

#### **Reading Data from Session:**

```java
// Get data (returns Object, need to cast)
String username = (String) session.getAttribute("user");
User user = (User) session.getAttribute("userObject");
Long loginTime = (Long) session.getAttribute("loginTime");
```

#### **Removing Data from Session:**

```java
// Remove specific attribute
session.removeAttribute("user");

// Destroy entire session (logout)
session.invalidate();
```

#### **Session Configuration:**

```java
// Set timeout (in seconds)
session.setMaxInactiveInterval(60 * 60); // 1 hour

// Get timeout
int timeout = session.getMaxInactiveInterval();

// Check if session is new
boolean isNew = session.isNew();

// Get session ID
String sessionId = session.getId();

// Get creation time
long creationTime = session.getCreationTime();

// Get last accessed time
long lastAccessed = session.getLastAccessedTime();
```

---

## 🍪 COOKIES - Detailed Explanation

### What is a Cookie?

A **cookie** is a small piece of data stored on the **client's browser**. The browser automatically sends cookies back to the server with each request.

### How Cookies Work:

```
1. Server creates cookie → Sends in response header
2. Browser stores cookie locally
3. Next request → Browser automatically sends cookie back
4. Server can read cookie from request
```

### Cookie Code Examples:

#### **Creating a Cookie:**

```java
// Create cookie (name, value)
Cookie cookie = new Cookie("username", "john");

// Set expiration (in seconds)
cookie.setMaxAge(60 * 60 * 24 * 7); // 7 days
// setMaxAge(0) = delete cookie
// setMaxAge(-1) = session cookie (deleted when browser closes)

// Set path (where cookie is available)
cookie.setPath("/"); // Available to entire app
cookie.setPath("/admin"); // Only available to /admin paths

// Security settings
cookie.setHttpOnly(true); // JavaScript can't access (security)
cookie.setSecure(true); // Only sent over HTTPS

// Add cookie to response
response.addCookie(cookie);
```

#### **Reading Cookies:**

```java
// Get all cookies from request
Cookie[] cookies = request.getCookies();

if(cookies != null) {
    for(Cookie cookie : cookies) {
        String name = cookie.getName();
        String value = cookie.getValue();
        
        if("username".equals(name)) {
            // Found the cookie we want
            String username = value;
        }
    }
}
```

#### **Deleting a Cookie:**

```java
// To delete, create new cookie with same name and maxAge = 0
Cookie deleteCookie = new Cookie("username", "");
deleteCookie.setMaxAge(0);
deleteCookie.setPath("/"); // Must match original path
response.addCookie(deleteCookie);
```

---

## 💡 Real-World Examples from Your Code

### Example 1: LoginServlet (Creating Session & Cookies)

```java
// After successful login:
HttpSession session = request.getSession();
session.setAttribute("user", username); // Store in session

// Create cookie for "Remember Me"
Cookie usernameCookie = new Cookie("rememberedUsername", username);
usernameCookie.setMaxAge(60 * 60 * 24 * 7); // 7 days
response.addCookie(usernameCookie);
```

### Example 2: DashboardServlet (Reading Session & Cookies)

```java
// Check if user is logged in (session)
HttpSession session = request.getSession(false);
if(session == null || session.getAttribute("user") == null) {
    response.sendRedirect("login");
    return;
}

// Read cookies
Cookie[] cookies = request.getCookies();
for(Cookie cookie : cookies) {
    if("rememberedUsername".equals(cookie.getName())) {
        String remembered = cookie.getValue();
    }
}
```

### Example 3: LogoutServlet (Clearing Both)

```java
// Clear session
HttpSession session = request.getSession(false);
if(session != null) {
    session.invalidate(); // Destroy session
}

// Clear cookies
Cookie[] cookies = request.getCookies();
for(Cookie cookie : cookies) {
    Cookie deleteCookie = new Cookie(cookie.getName(), "");
    deleteCookie.setMaxAge(0);
    deleteCookie.setPath("/");
    response.addCookie(deleteCookie);
}
```

---

## 🔒 Security Best Practices

### Sessions:
- ✅ Always use `getSession(false)` when checking authentication (don't create new session)
- ✅ Invalidate session on logout
- ✅ Set appropriate timeout values
- ✅ Don't store sensitive data in session if possible

### Cookies:
- ✅ Use `setHttpOnly(true)` to prevent JavaScript access
- ✅ Use `setSecure(true)` in production (HTTPS only)
- ✅ Don't store passwords or sensitive data in cookies
- ✅ Set appropriate expiration times
- ✅ Use secure, random values for session IDs

---

## 🎓 Common Patterns

### Pattern 1: "Remember Me" Functionality

```java
// LoginServlet - Create cookie
if(rememberMe) {
    Cookie cookie = new Cookie("rememberedUsername", username);
    cookie.setMaxAge(60 * 60 * 24 * 30); // 30 days
    response.addCookie(cookie);
}

// LoginServlet - Read cookie on GET
Cookie[] cookies = request.getCookies();
if(cookies != null) {
    for(Cookie cookie : cookies) {
        if("rememberedUsername".equals(cookie.getName())) {
            request.setAttribute("rememberedUsername", cookie.getValue());
        }
    }
}
```

### Pattern 2: Session-Based Authentication

```java
// Check authentication
HttpSession session = request.getSession(false);
if(session == null || session.getAttribute("user") == null) {
    response.sendRedirect("login");
    return;
}

// User is authenticated, proceed
String username = (String) session.getAttribute("user");
```

### Pattern 3: Shopping Cart

```java
// Add to cart (session)
HttpSession session = request.getSession();
List<Item> cart = (List<Item>) session.getAttribute("cart");
if(cart == null) {
    cart = new ArrayList<>();
}
cart.add(item);
session.setAttribute("cart", cart);
```

---

## ❓ FAQ

**Q: Can I use both sessions and cookies together?**  
A: Yes! They complement each other. Use sessions for authentication, cookies for preferences.

**Q: What happens if user disables cookies?**  
A: Sessions won't work (they rely on JSESSIONID cookie). You'll need URL rewriting.

**Q: How long do sessions last?**  
A: Default is 30 minutes of inactivity. You can configure this.

**Q: Can cookies store objects?**  
A: No, only strings. You'd need to serialize objects to JSON/string first.

**Q: Are sessions thread-safe?**  
A: Yes, but be careful with concurrent access to session attributes.

---

## 📝 Summary

- **Sessions** = Server-side storage, more secure, for authentication
- **Cookies** = Client-side storage, less secure, for preferences
- Use **sessions** for login status and sensitive data
- Use **cookies** for "Remember Me" and user preferences
- Always invalidate sessions on logout
- Use secure cookie settings in production

---

For code examples, see:
- `LoginServlet.java` - Creating sessions and cookies
- `DashboardServlet.java` - Reading sessions and cookies  
- `LogoutServlet.java` - Clearing sessions and cookies
