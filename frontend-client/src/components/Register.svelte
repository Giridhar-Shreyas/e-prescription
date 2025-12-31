<script>
    const BACKEND_URL = import.meta.env.PUBLIC_BACKEND_URL || 'http://localhost:8080';
    
    let formData = {
      username: '',
      email: '',
      password: '',
      role: 'user',
      firstName: '',
      lastName: '',
      phone: ''
    };
    
    let loading = false;
    let error = '';
    let success = false;
    
    async function handleSubmit() {
      loading = true;
      error = '';
      
      try {
        const response = await fetch(`${BACKEND_URL}/api/auth/register`, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(formData),
        });
        
        const data = await response.json();
        
        if (response.ok) {
          success = true;
          setTimeout(() => {
            window.location.href = '/';
          }, 2000);
        } else {
          error = data.error || 'Registration failed';
        }
      } catch (err) {
        error = 'Network error. Please try again.';
      } finally {
        loading = false;
      }
    }
  </script>
  
  <div class="register-container">
    <div class="register-card">
      <h1>Register</h1>
      
      {#if success}
        <div class="success">
          Registration successful! Redirecting to login...
        </div>
      {:else}
        <form on:submit|preventDefault={handleSubmit}>
          <div class="form-group">
            <label for="username">Username</label>
            <input type="text" id="username" bind:value={formData.username} required />
          </div>
          
          <div class="form-group">
            <label for="email">Email</label>
            <input type="email" id="email" bind:value={formData.email} required />
          </div>
          
          <div class="form-group">
            <label for="password">Password</label>
            <input type="password" id="password" bind:value={formData.password} required minlength="6" />
          </div>
          
          <div class="form-group">
            <label for="role">Role</label>
            <select id="role" bind:value={formData.role} required>
              <option value="user">User</option>
              <option value="doctor">Doctor</option>
              <option value="pharmacist">Pharmacist</option>
            </select>
          </div>
          
          <div class="form-row">
            <div class="form-group">
              <label for="firstName">First Name</label>
              <input type="text" id="firstName" bind:value={formData.firstName} required />
            </div>
            
            <div class="form-group">
              <label for="lastName">Last Name</label>
              <input type="text" id="lastName" bind:value={formData.lastName} required />
            </div>
          </div>
          
          <div class="form-group">
            <label for="phone">Phone</label>
            <input type="tel" id="phone" bind:value={formData.phone} />
          </div>
          
          {#if error}
            <div class="error">{error}</div>
          {/if}
          
          <button type="submit" disabled={loading}>
            {loading ? 'Registering...' : 'Register'}
          </button>
          
          <p class="login-link">
            Already have an account? <a href="/">Login</a>
          </p>
        </form>
      {/if}
    </div>
  </div>
  
  <style>
    .register-container {
      min-height: 100vh;
      display: flex;
      justify-content: center;
      align-items: center;
      background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
      padding: 2rem;
    }
  
    .register-card {
      background: white;
      padding: 2rem;
      border-radius: 12px;
      box-shadow: 0 10px 40px rgba(0, 0, 0, 0.2);
      width: 100%;
      max-width: 500px;
    }
  
    h1 {
      margin-bottom: 1.5rem;
      color: #333;
      text-align: center;
    }
  
    form {
      display: flex;
      flex-direction: column;
      gap: 1rem;
    }
  
    .form-group {
      display: flex;
      flex-direction: column;
      gap: 0.5rem;
    }
  
    .form-row {
      display: grid;
      grid-template-columns: 1fr 1fr;
      gap: 1rem;
    }
  
    label {
      font-weight: 500;
      color: #555;
    }
  
    input, select {
      padding: 0.75rem;
      border: 1px solid #ddd;
      border-radius: 6px;
      font-size: 1rem;
    }
  
    input:focus, select:focus {
      outline: none;
      border-color: #667eea;
    }
  
    button {
      background: #667eea;
      color: white;
      border: none;
      padding: 1rem;
      font-size: 1rem;
      border-radius: 8px;
      cursor: pointer;
      transition: all 0.3s ease;
      margin-top: 1rem;
    }
  
    button:hover:not(:disabled) {
      background: #5568d3;
      transform: translateY(-2px);
    }
  
    button:disabled {
      opacity: 0.6;
      cursor: not-allowed;
    }
  
    .error {
      background: #fee;
      color: #c33;
      padding: 0.75rem;
      border-radius: 6px;
      text-align: center;
    }
  
    .success {
      background: #efe;
      color: #3c3;
      padding: 1rem;
      border-radius: 6px;
      text-align: center;
    }
  
    .login-link {
      text-align: center;
      color: #666;
      margin-top: 1rem;
    }
  
    .login-link a {
      color: #667eea;
      text-decoration: none;
    }
  
    .login-link a:hover {
      text-decoration: underline;
    }
  </style>