import React, { useState } from 'react';
import './App.css';
import OrderForm from './OrderForm';
import OrderSummary from './OrderSummary';

function App() {
  const [order, setOrder] = useState(null);

  return (
    <div className="app-container">
      <h1 className="header">☕ Coffee Vending Machine</h1>
      <div className="form-container">
        {!order ? (
          <OrderForm setOrder={setOrder} />
        ) : (
          <OrderSummary order={order} onReset={() => setOrder(null)} />
        )}
      </div>
    </div>
  );
}

export default App;
