import React from "react";
import styled from "styled-components";

interface Props {
  className: any;
}

const StyledArrowLeft1 = styled.svg`
  &.arrow-left-1 {
    fill: none;
    height: 24px;
    width: 24px;
  }

  & .path {
    fill: white;
  }
`;

export const ArrowLeft1 = ({ className }: Props): JSX.Element => {
  return (
    <StyledArrowLeft1
      className={`arrow-left-1 ${className}`}
      xmlns="http://www.w3.org/2000/svg"
      viewBox="0 0 24 24"
    >
      <path className="path" d="M10 17L15 12L10 7L10 17Z" />
    </StyledArrowLeft1>
  );
};
