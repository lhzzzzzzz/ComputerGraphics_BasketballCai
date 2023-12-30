# Project Instructions

## Controls

- **A**: Move towards the basketball cart.
- **S**: Move towards the basketball hoop.
- **E**: Dribble the ball once.
- **Spacebar (Long Press)**: Shoot. A 1-2 second long press results in a successful shot; otherwise, the shot will miss.
- **D**: Return to the basketball cart to retrieve the ball.
- **F**: End the game.

## Gameplay Flow

```mermaid
graph TD
    A[Start: Stand still at starting position] -->|Press A| B[Get a basketball from the cart]
    B -->|Press S|C[Stand in front of the cart with the basketball]
    C -->|Press E|I[Dribble the ball]
		C -->|Press SpaceBar|D[Shoot the ball]
		D -->|success|E[success shooting]
		D -->|fail|F[fail shooting]
		E -->G[waiting for next step]
		F -->G
		G -->|Press D|C
    G -->|Press F|H[End the game]

```





## Repository

For more information and updates, visit our [GitHub Repository](https://github.com/lhzzzzzzz/ComputerGraphics_BasketballCai).