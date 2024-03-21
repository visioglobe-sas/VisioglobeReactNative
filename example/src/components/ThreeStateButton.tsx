import PropTypes from "prop-types";
import React from "react";
import { useReducer } from "react";
import styled from "styled-components/native";
import { ArrowLeft1 } from "./ArrowLeft1";
import { Text } from "react-native";


interface Props {
  status: "two" | "alternative-version" | "three" | "one";
  text: string;
}

const StyledSteps = styled.View`
  align-items: flex-start;
  border: 1px solid;
  border-radius: 8px;
  display: inline-flex;
  gap: 8px;
  height: 104px;
  overflow: hidden;
  position: relative;`;

const StyledText = styled.Text`
    color: #ffffff;
    font-size: 19px;
    font-weight: 600;
    letter-spacing: 0.03px;
    line-height: 28px;
    position: relative;
    white-space: nowrap;
    width: fit-content;`;


const Frame = styled.View`
    align-items: center;
    display: inline-flex;
    flex: 0 0 auto;
    gap: 8px;
    justify-content: center;
    position: relative;`;

const Div = styled.View`
      align-items: center;
      display: inline-flex;
      flex: 0 0 auto;
      gap: 4px;
      height: 24px;
      justify-content: center;
      position: relative;`;
      
const Ellipse = styled.View`
        border-radius: 4px;
        height: 8px;
        position: relative;
        width: 8px;`;

const Ellipse2 = styled.View`
        border-radius: 4px;
        height: 8px;
        position: relative;
        width: 8px;`;

const Ellipse3 = styled.View`
        border-radius: 4px;
        height: 8px;
        position: relative;
        width: 8px;`;

const Element = styled.Text`
      position: relative;
      white-space: nowrap;
      width: fit-content;`;


const Arrowleftwrapper = styled.View`
    align-items: center;
    align-self: stretch;
    border-right-style: solid;
    border-right-width: 1px;
    display: flex;
    gap: 8px;
    justify-content: center;
    margin-bottom: -1px;
    margin-left: -1px;
    margin-top: -1px;
    padding: 24px 8px;
    position: relative;
    width: 50px;`;
        

const Arrowleft = styled.View`
      height: 24px !important;
      position: relative !important;
      width: 24px !important;`;


const Frame2 = styled.View`
    align-items: center;
    align-self: stretch;
    display: inline-flex;
    flex: 0 0 auto;
    flex-direction: column;
    gap: 1px;
    justify-content: center;
    padding: 0px 8px;
    position: relative;`;
  
   
const Arrowleft1wrapper = styled.View`
    align-items: center;
    align-self: stretch;
    border-left-style: solid;
    border-left-width: 1px;
    display: flex;
    gap: 8px;
    justify-content: center;
    margin-bottom: -1px;
    margin-top: -1px;
    padding: 24px 8px;
    position: relative;
    width: 50px;`
  /*

  &.two {
    flex-direction: column;
    justify-content: center;
    padding: 16px 24px;

    & .ellipse {
      background-color: #d9d9d9;
    }

    & .ellipse-2 {
      background-color: #0094f0;
    }

    & .ellipse-3 {
      background-color: #d9d9d9;
    }
  }

  &.one {
    flex-direction: column;
    justify-content: center;
    padding: 16px 24px;

    & .ellipse {
      background-color: var(--tokens-surfaces-surface-brand);
    }

    & .ellipse-2 {
      background-color: #d9d9d9;
    }

    & .ellipse-3 {
      background-color: #d9d9d9;
    }
  }

  &.three {
    flex-direction: column;
    justify-content: center;
    padding: 16px 24px;

    & .ellipse {
      background-color: #d9d9d9;
    }

    & .ellipse-2 {
      background-color: #d9d9d9;
    }

    & .ellipse-3 {
      background-color: #0094f0;
    }
  }
`;*/

export const Steps = ({ status, text = "1/3" }: Props): JSX.Element => {
  const [state, dispatch] = useReducer(reducer, {
    status: status || "one",
  });

  return (
    <StyledSteps
      onTouchStart={() => {
        dispatch("click");
      }}
    >
      {["one", "three", "two"].includes(state.status) && (
        <>
          <StyledText>Animate 3D</StyledText>
          <Frame>
            <Div>
              <Ellipse/>
              <Ellipse2/>
              <Ellipse3/>
            </Div>
            <Element>
              {state.status === "one" && <>{text}</>}

              {state.status === "two" && <>2/3</>}

              {state.status === "three" && <>3/3</>}
            </Element>
          </Frame>
        </>
      )}

      {state.status === "alternative-version" && (
        <>
          <Arrowleftwrapper>
            <Arrowleft/>
          </Arrowleftwrapper>
          <Frame2>
            <StyledText>Animate 3D</StyledText>
            <Element>{text}</Element>
          </Frame2>
          <Arrowleft1wrapper>
            <Arrowleft/>
          </Arrowleft1wrapper>
        </>
      )}
    </StyledSteps>
  );
};

function reducer(state: any, action: any) {
  if (state.status === "one") {
    switch (action) {
      case "click":
        return {
          status: "two",
        };
    }
  }

  if (state.status === "alternative-version") {
    switch (action) {
      case "click":
        return {
          status: "two",
        };
    }
  }

  if (state.status === "two") {
    switch (action) {
      case "click":
        return {
          status: "three",
        };
    }
  }

  if (state.status === "three") {
    switch (action) {
      case "click":
        return {
          status: "one",
        };
    }
  }

  return state;
}

Steps.propTypes = {
  status: PropTypes.oneOf(["two", "alternative-version", "three", "one"]),
  text: PropTypes.string,
};