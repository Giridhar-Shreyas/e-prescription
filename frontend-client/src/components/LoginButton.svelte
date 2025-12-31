<script>
  const BACKEND_URL = import.meta.env.PUBLIC_BACKEND_URL || 'http://localhost:8080';
  
  let loading = false;
  
  async function handleLogin() {
    loading = true;
    
    try {
      // Get login URL from Spring Boot
      const response = await fetch(`${BACKEND_URL}/api/auth/login`);
      const data = await response.json();
      
      // Redirect to Keycloak
      window.location.href = data.loginUrl;
    } catch (error) {
      console.error('Failed to get login URL:', error);
      loading = false;
    }
  }
</script>

<div class="login-container">
  <div class="login-card">
    <h1>E-Prescription System</h1>
    <p>Secure digital prescription management</p>
    <button on:click={handleLogin} class="login-btn" disabled={loading}>
      {loading ? 'Redirecting...' : 'Login with Keycloak'}
    </button>
    <p class="register-link">
      Don't have an account? <a href="/register">Register</a>
    </p>
  </div>
</div>

<style>
  .login-container {
    padding: 2rem;
  }

  .login-card {
    background: white;
    padding: 3rem;
    border-radius: 12px;
    box-shadow: 0 10px 40px rgba(0, 0, 0, 0.2);
    text-align: center;
    max-width: 400px;
  }

  h1 {
    color: #333;
    margin-bottom: 0.5rem;
    font-size: 2rem;
  }

  p {
    color: #666;
    margin-bottom: 2rem;
  }

  .login-btn {
    background: #667eea;
    color: white;
    border: none;
    padding: 1rem 2rem;
    font-size: 1rem;
    border-radius: 8px;
    cursor: pointer;
    transition: all 0.3s ease;
    width: 100%;
  }

  .login-btn:hover:not(:disabled) {
    background: #5568d3;
    transform: translateY(-2px);
    box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
  }

  .login-btn:disabled {
    opacity: 0.6;
    cursor: not-allowed;
  }

  .register-link {
    margin-top: 1.5rem;
    font-size: 0.9rem;
  }

  .register-link a {
    color: #667eea;
    text-decoration: none;
  }

  .register-link a:hover {
    text-decoration: underline;
  }
</style>